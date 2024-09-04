package com.example.bookapp.models

data class Book(
    val id: Int? = null,
    val title: String,
    val publisher: String,
    val genre: String,
    val coverImage: String? = null,
    val userId: Int
)