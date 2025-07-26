package com.elitec.compralibre.presentantion.screen.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF42A5F5),         // Azul oscuro para el botón "Create Listing"
    onPrimary = Color.Black,             // Texto negro sobre el botón
    primaryContainer = Color(0xFFBBDEFB), // Variante clara del azul para contenedores
    secondary = Color(0xFFB0B0B0),       // Gris claro para texto secundario
    onSecondary = Color.White,           // Texto blanco sobre secundario
    secondaryContainer = Color(0xFF2E2E2E), // Gris medio para categorías
    background = Color(0xFF121212),      // Fondo principal oscuro
    onBackground = Color.White,          // Texto principal blanco
    surface = Color(0xFF1E1E1E),         // Fondo de la barra de búsqueda
    onSurface = Color(0xFFB0B0B0),       // Texto en superficies
    error = Color(0xFFCF6679),          // Color de error adaptado (rosa oscuro)
    onError = Color.White                // Texto sobre error
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2),         // Azul del botón "Create Listing"
    onPrimary = Color.White,             // Texto blanco sobre el botón
    primaryContainer = Color(0xFFBBDEFB), // Variante más clara del azul para contenedores
    secondary = Color(0xFF757575),       // Gris oscuro para texto secundario
    onSecondary = Color.Black,           // Texto negro sobre secundario
    secondaryContainer = Color(0xFFE0E0E0), // Gris claro para categorías
    background = Color.White,            // Fondo principal
    onBackground = Color.Black,          // Texto principal
    surface = Color(0xFFF5F5F5),         // Fondo de la barra de búsqueda
    onSurface = Color(0xFF757575),       // Texto en superficies
    error = Color(0xFFB00020),          // Color de error (estándar Material 3)
    onError = Color.White                // Texto sobre error
)

@Composable
fun CompraLibreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}