package com.basic.childrenbook.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.childrenbook.data.repository.BookRepository
import com.basic.childrenbook.domain.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class AgeBracket(val title: String, val query: String, val subtitle: String, val emoji: String) {
    ZERO_TO_THREE("0 – 3 Months", "subject:\"high contrast books newborn\"", "High contrast & visual tracking", "👀"),
    THREE_TO_SIX("3 – 6 Months", "toddler cloth crinkle books", "Cloth & crinkle sensory books", "✋"),
    SIX_TO_TWELVE("6 – 12 Months", "subject:\"board books\"", "Touch-and-feel, lift-the-flap board books", "🧸"),
    ONE_TO_TWO("1 – 2 Years", "first words baby books", "First words & animal sounds", "🎨")
}

sealed interface BabyBookUiState {
    data object Home : BabyBookUiState
    data class AgeSelectorDialog(val selectedAge: AgeBracket = AgeBracket.SIX_TO_TWELVE) : BabyBookUiState
    data class BookList(
        val currentAge: AgeBracket,
        val books: List<Book>,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : BabyBookUiState
}

@HiltViewModel
class BabyBookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BabyBookUiState>(BabyBookUiState.Home)
    val uiState: StateFlow<BabyBookUiState> = _uiState.asStateFlow()

    fun onBabyBookClicked() {
        _uiState.value = BabyBookUiState.AgeSelectorDialog(AgeBracket.SIX_TO_TWELVE)
    }

    fun onDismissDialog() {
        _uiState.value = BabyBookUiState.Home
    }

    fun selectAgeAndLoad(age: AgeBracket) {
        _uiState.value = BabyBookUiState.BookList(currentAge = age, books = emptyList(), isLoading = true)
        viewModelScope.launch {
            repository.searchBooks(age.query)
                .onSuccess { books ->
                    _uiState.value = BabyBookUiState.BookList(currentAge = age, books = books, isLoading = false)
                }
                .onFailure { throwable ->
                    _uiState.value = BabyBookUiState.BookList(
                        currentAge = age,
                        books = emptyList(),
                        isLoading = false,
                        errorMessage = throwable.localizedMessage ?: "Failed to load books"
                    )
                }
        }
    }

    fun switchAge(age: AgeBracket) {
        selectAgeAndLoad(age)
    }

    fun goHome() {
        _uiState.value = BabyBookUiState.Home
    }
}
