package com.basic.childrenbook.data.remote

import com.basic.childrenbook.BuildConfig
import com.basic.childrenbook.data.remote.dto.BooksResponseDto
import com.basic.childrenbook.data.remote.dto.VolumeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {

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
