package com.elitec.compralibre.presentantion.util

import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonNavItems(
    val tittle: String,
    val icon: ImageVector,
    val action: (String)-> Unit
)
