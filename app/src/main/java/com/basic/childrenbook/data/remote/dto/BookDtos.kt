package com.basic.childrenbook.data.remote.dto

import kotlinx.serialization.Serializable

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
