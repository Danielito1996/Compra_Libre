package com.elitec.compralibre.presentantion.util

data class CategoriesButtonItem(
    val tittle: String,
    val description: String,
    val action: (String) -> Unit
)
