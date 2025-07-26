package com.elitec.compralibre.presentantion.util

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class DeviceConfiguration {
    SMART_CLOCK,
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    AUTOMOBILE_DEVICE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    companion object {
        fun fromWindowsSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            val withClass = windowSizeClass.windowWidthSizeClass
            val heightClass = windowSizeClass.windowHeightSizeClass

            return when {
                // Altura compacta , Ancho compacto -> Reloj Inteligente
                withClass == WindowWidthSizeClass.COMPACT &&
                        heightClass == WindowHeightSizeClass.COMPACT -> SMART_CLOCK
                // Altura media , Ancho compacto -> Movil vertical
                withClass == WindowWidthSizeClass.COMPACT &&
                        heightClass == WindowHeightSizeClass.MEDIUM -> MOBILE_PORTRAIT
                // Altura expandida, Ancho compacto -> Movil vertical
                withClass == WindowWidthSizeClass.COMPACT &&
                        heightClass == WindowHeightSizeClass.EXPANDED -> MOBILE_PORTRAIT
                // Altura compacta, Ancho Medio -> Movil horizontal
                withClass == WindowWidthSizeClass.MEDIUM &&
                        heightClass == WindowHeightSizeClass.COMPACT -> MOBILE_LANDSCAPE
                // Altura compacta, Ancho expandido -> Movil horizontal
                withClass == WindowWidthSizeClass.EXPANDED &&
                        heightClass == WindowHeightSizeClass.COMPACT -> MOBILE_LANDSCAPE
                // Altura expandida, Ancho medio -> Tablet vertical
                withClass == WindowWidthSizeClass.MEDIUM &&
                        heightClass == WindowHeightSizeClass.EXPANDED -> TABLET_PORTRAIT
                // Altura media, Ancho expandido -> Tablet horizontal
                withClass == WindowWidthSizeClass.EXPANDED &&
                        heightClass == WindowHeightSizeClass.MEDIUM -> TABLET_LANDSCAPE
                // Altura media, Ancho medio-> Dispositivo de automovil
                withClass == WindowWidthSizeClass.MEDIUM &&
                        heightClass == WindowHeightSizeClass.MEDIUM -> AUTOMOBILE_DEVICE
                else -> {
                    DESKTOP
                }
            }
        }
    }
}