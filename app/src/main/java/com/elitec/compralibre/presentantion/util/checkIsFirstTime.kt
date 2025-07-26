package com.elitec.compralibre.presentantion.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.core.content.edit

// Clave para SharedPreferences
private const val PREFS_NAME = "HavanaPrefs"
private const val KEY_IS_FIRST_TIME = "isFirstTime"

// Función para verificar y actualizar el estado de primera entrada
@Composable
fun checkIsFirstTime(context: Context): Boolean {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val isFirstTime = prefs.getBoolean(KEY_IS_FIRST_TIME, true)
    if (isFirstTime) {
        // Marca que el usuario ya ha visto el onboarding
        prefs.edit { putBoolean(KEY_IS_FIRST_TIME, false) }
    }
    return isFirstTime
}