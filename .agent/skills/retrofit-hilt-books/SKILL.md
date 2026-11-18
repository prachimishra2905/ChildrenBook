---
name: retrofit-hilt-books
description: Implement or extend network calls in this Android app using Retrofit, kotlinx.serialization and Hilt dependency injection, centered on the BookApi interface (Google Books API, toddler 0-3 book lists). Use this skill whenever the user asks to add a book endpoint, fetch books, create a repository or ViewModel for books, set up Retrofit or Hilt, fix a network error, or add any API call, even if they don't mention Retrofit or Hilt by name.
---

# Retrofit + Hilt implementation (BookApi)

## Stack (do not substitute)
- Retrofit + OkHttp + kotlinx.serialization (no Gson, Moshi or RxJava)
- Hilt for dependency injection (KSP, not kapt)
- Coroutines: every API function is `suspend`
- Jetpack Compose UI, MVVM: Composable -> ViewModel -> Repository -> BookApi
- Never call the network from a Composable or Activity

## Dependencies
Use the latest stable versions. Check them before adding.

Plugins (module build.gradle.xbvnnhgggkts): `com.android.application`, `org.jetbrains.kotlin.android`, `org.jetbrains.kotlin.plugin.serialization`, `com.google.dagger.hilt.android`, `com.google.devtools.ksp`.


## One-time setup
1. `AndroidManifest.xml`: add `<uses-permission android:name="android.permission.INTERNET" />` and `android:name=".App"` on `<application>`.
2. Application class:
```kotlin
@HiltAndroidApp
class App : Application()
```
3. Every entry point Activity gets `@AndroidEntryPoint`.
4. API key: put `BOOKS_API_KEY=xxxx` in `local.properties` (never commit it), then expose it with BuildConfig:
```kotlin
// build.gradle.kts (android block)
buildFeatures { buildConfig = true }
defaultConfig {
    val key = gradleLocalProperties(rootDir, providers).getProperty("BOOKS_API_KEY", "")
    buildConfigField("String", "BOOKS_API_KEY", "\"$key\"")
    buildConfigField("String", "BOOKS_BASE_URL", "\"https://www.googleapis.com/\"")
}
```

## Folder layout
```
data/remote/BookApi.kt            Retrofit interface
data/remote/dto/BookDtos.kt       @Serializable response models (suffix Dto)
data/repository/BookRepository.kt
di/NetworkModule.kt               Hilt module (Retrofit, OkHttp, BookApi)
domain/Book.kt                    UI model
ui/books/BooksViewModel.kt
```

## BookApi interface
Add every new endpoint here. One suspend function per endpoint.
```kotlin
interface BookApi {

    // Toddler books: pass q like  subject:"board books"  or  toddler bedtime stories
    @GET("books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("startIndex") startIndex: Int = 0,
        @Query("langRestrict") lang: String = "en",
        @Query("printType") printType: String = "books",
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BooksResponseDto

    @GET("books/v1/volumes/{id}")
    suspend fun getBook(
        @Path("id") id: String,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): VolumeDto
}
```

## DTOs (kotlinx.serialization)
Make every field nullable or defaulted, because the API often omits fields.
```kotlin
@Serializable
data class BooksResponseDto(
    val totalItems: Int = 0,
    val items: List<VolumeDto> = emptyList()
)

@Serializable
data class VolumeDto(
    val id: String,
    val volumeInfo: VolumeInfoDto = VolumeInfoDto()
)

@Serializable
data class VolumeInfoDto(
    val title: String? = null,
    val authors: List<String>? = null,
    val description: String? = null,
    val publishedDate: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val imageLinks: ImageLinksDto? = null
)

@Serializable
data class ImageLinksDto(
    val thumbnail: String? = null,
    val smallThumbnail: String? = null
)
```

## Domain model and mapper
Never expose DTOs to the UI. Google returns `http://` image links, so convert them to `https://`.
```kotlin
data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val description: String,
    val coverUrl: String?
)

fun VolumeDto.toDomain() = Book(
    id = id,
    title = volumeInfo.title.orEmpty(),
    authors = volumeInfo.authors?.joinToString() ?: "Unknown",
    description = volumeInfo.description.orEmpty(),
    coverUrl = volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
)
```

## Hilt module (NetworkModule)
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC // never BODY with keys in URLs
                })
            }
        }
        .build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BOOKS_BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)
}
```

## Repository
```kotlin
class BookRepository @Inject constructor(private val api: BookApi) {

    suspend fun searchBooks(query: String): Result<List<Book>> =
        runCatching { api.searchBooks(query).items.map { it.toDomain() } }
}
```
Catch `CancellationException` and rethrow it if you replace `runCatching` with a manual try/catch.

## ViewModel
```kotlin
sealed interface BooksUiState {
    data object Loading : BooksUiState
    data class Success(val books: List<Book>) : BooksUiState
    data class Error(val message: String) : BooksUiState
}

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val state: StateFlow<BooksUiState> = _state.asStateFlow()

    init { load("subject:\"board books\"") }

    fun load(query: String) = viewModelScope.launch {
        _state.value = BooksUiState.Loading
        repository.searchBooks(query)
            .onSuccess { _state.value = BooksUiState.Success(it) }
            .onFailure { _state.value = BooksUiState.Error(it.toUserMessage()) }
    }
}

fun Throwable.toUserMessage() = when (this) {
    is UnknownHostException -> "No internet connection"
    is SocketTimeoutException -> "Request timed out"
    is HttpException -> "Server error (${code()})"
    else -> "Something went wrong"
}
```
In a Composable: `val vm: BooksViewModel = hiltViewModel()` then `val state by vm.state.collectAsStateWithLifecycle()`.

## Steps to add a new endpoint
1. Add `@Serializable` DTOs (nullable or defaulted fields).
2. Add a `suspend fun` with the right annotation to `BookApi`.
3. Add a mapper to a domain model.
4. Add a repository function returning `Result<T>`.
5. Expose it from the ViewModel through `StateFlow<UiState>`.
6. Show Loading, Success and Error states in the UI.

## Toddler (0-3) query ideas
Google Books has no age filter. Search with these and filter in code if needed:
- `subject:"board books"`
- `toddler bedtime stories`
- `nursery rhymes baby`
- `first words baby`

## Rules
- No hardcoded base URL or API key in code. Use BuildConfig.
- Hilt provides all dependencies; never create Retrofit or OkHttp manually elsewhere.
- Do not use `GlobalScope`; use `viewModelScope`.
- Do not log full request URLs in release builds (the key is in the query).
- If the build fails after a change, check Hilt annotations (`@HiltAndroidApp`, `@AndroidEntryPoint`, `@HiltViewModel`) and the KSP plugin first.
