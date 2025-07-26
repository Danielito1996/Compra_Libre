package com.elitec.compralibre.domain.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoriesItem(
    val id: String,
    val tittle: String,
    val description: String,
    var icon: ImageVector = Icons.Default.Fastfood
)
