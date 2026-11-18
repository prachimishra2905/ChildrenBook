package com.basic.childrenbook.domain

import com.basic.childrenbook.data.remote.dto.VolumeDto
import java.net.URLEncoder

data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val description: String,
    val coverUrl: String?,
    val category: String = "Board Book",
    val rating: String = "★ 4.9 (10k+)",
    val price: String = "$7.99",
    val amazonUrl: String = "https://www.amazon.com/s?k=Baby+Books"
)

fun VolumeDto.toDomain(): Book {
    val titleStr = volumeInfo.title.orEmpty()
    val encodedTitle = try {
        URLEncoder.encode(titleStr, "UTF-8")
    } catch (e: Exception) {
        "Baby+Book"
    }
    return Book(
        id = id,
        title = titleStr,
        authors = volumeInfo.authors?.joinToString() ?: "Unknown Author",
        description = volumeInfo.description.orEmpty(),
        coverUrl = volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"),
        category = volumeInfo.categories?.firstOrNull() ?: "Board Book",
        rating = "★ 4.8 (12k)",
        price = "$7.99",
        amazonUrl = "https://www.amazon.com/s?k=$encodedTitle"
    )
}
