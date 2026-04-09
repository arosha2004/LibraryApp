package com.example.libraryapp.model

import androidx.annotation.DrawableRes

data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val description: String,
    @DrawableRes val image: Int
)
