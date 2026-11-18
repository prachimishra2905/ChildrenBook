package com.basic.childrenbook.domain.model

data class Book(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val coverUrl: String = "",
    val pages: List<String> = emptyList()
)
