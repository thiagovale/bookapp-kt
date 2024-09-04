package com.example.bookapp.models;

data class User(
        val id: Int? = null,
        val name: String,
        val email: String,
        val profileImage: String? = null
)