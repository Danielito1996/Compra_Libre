package com.elitec.compralibre.presentantion.screen.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Roboto = FontFamily.Default // O carga una fuente personalizada desde res/font/

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Títulos muy grandes en banners promocionales o pantallas de bienvenida
        fontWeight = FontWeight.Normal, // Peso normal
        fontSize = 57.sp,              // Tamaño para destacar contenido principal en pantallas grandes
        lineHeight = 64.sp,            // Altura de línea para legibilidad
        letterSpacing = (-0.25).sp     // Espaciado negativo para compactación
    ),
    displayMedium = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Encabezados de secciones destacadas o promociones
        fontWeight = FontWeight.Normal, // Peso normal
        fontSize = 45.sp,              // Tamaño mediano para captar atención
        lineHeight = 52.sp,            // Altura de línea ajustada
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    displaySmall = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Subtítulos de promociones o anuncios
        fontWeight = FontWeight.Normal, // Peso normal
        fontSize = 36.sp,              // Tamaño pequeño para información adicional
        lineHeight = 44.sp,            // Altura de línea para legibilidad
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    headlineLarge = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Título principal de la pantalla ("Havana")
        fontWeight = FontWeight.SemiBold, // 600 - Peso semi-negrita
        fontSize = 32.sp,              // Tamaño para el nombre de la app
        lineHeight = 40.sp,            // Altura de línea ajustada
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    headlineMedium = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Títulos de pantallas secundarias o categorías importantes
        fontWeight = FontWeight.SemiBold, // 600 - Peso semi-negrita
        fontSize = 28.sp,              // Tamaño para encabezados de secciones internas
        lineHeight = 36.sp,            // Altura de línea ajustada
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    headlineSmall = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Títulos de secciones ("Featured Listings", "Nearby Listings")
        fontWeight = FontWeight.SemiBold, // 600 - Peso semi-negrita
        fontSize = 24.sp,              // Tamaño para encabezados de listados
        lineHeight = 32.sp,            // Altura de línea ajustada
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    titleLarge = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Texto de botones principales ("Create Listing")
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 22.sp,              // Tamaño para botones destacados
        lineHeight = 28.sp,            // Altura de línea ajustada
        letterSpacing = 0.sp           // Espaciado neutro
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Descripciones cortas en cards o secciones
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 16.sp,              // Tamaño para texto informativo breve
        lineHeight = 24.sp,            // Altura de línea ajustada
        letterSpacing = 0.1.sp         // Espaciado ligero
    ),
    titleSmall = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Texto de botones de filtro ("Food", "Services", etc.)
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 14.sp,              // Tamaño para botones compactos
        lineHeight = 20.sp,            // Altura de línea ajustada
        letterSpacing = 0.1.sp         // Espaciado ligero
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Placeholder de búsqueda
        fontWeight = FontWeight.Normal, // 400 - Peso normal
        fontSize = 16.sp,              // Tamaño para texto en campos de entrada
        lineHeight = 24.sp,            // Altura de línea ajustada
        letterSpacing = 0.5.sp         // Espaciado moderado
    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Subtítulos como precios y distancias
        fontWeight = FontWeight.Normal, // 400 - Peso normal
        fontSize = 14.sp,              // Tamaño para datos secundarios
        lineHeight = 20.sp,            // Altura de línea ajustada
        letterSpacing = 0.25.sp        // Espaciado ligero
    ),
    bodySmall = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Notas pequeñas o disclaimers
        fontWeight = FontWeight.Normal, // 400 - Peso normal
        fontSize = 12.sp,              // Tamaño para texto auxiliar
        lineHeight = 16.sp,            // Altura de línea ajustada
        letterSpacing = 0.4.sp         // Espaciado moderado
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Etiquetas en formularios o descripciones cortas
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 14.sp,              // Tamaño para etiquetas informativas
        lineHeight = 20.sp,            // Altura de línea ajustada
        letterSpacing = 0.1.sp         // Espaciado ligero
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Texto en la barra de navegación inferior ("Home", "Search", etc.)
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 12.sp,              // Tamaño para navegación
        lineHeight = 16.sp,            // Altura de línea ajustada
        letterSpacing = 0.5.sp         // Espaciado moderado
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,           // Caso de uso: Etiquetas muy pequeñas como pie de página
        fontWeight = FontWeight.Medium, // 500 - Peso medio
        fontSize = 11.sp,              // Tamaño para texto legal o créditos
        lineHeight = 16.sp,            // Altura de línea ajustada
        letterSpacing = 0.5.sp         // Espaciado moderado
    )
)