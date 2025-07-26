package com.elitec.compralibre.presentantion.navigation

import kotlinx.serialization.Serializable

interface MainScreen

@Serializable
object Splash: MainScreen

@Serializable
object Register: MainScreen

@Serializable
object Login: MainScreen

@Serializable
object Presentation: MainScreen

@Serializable
object PassRecovery: MainScreen

@Serializable
data class MainHome(
    val userId: String
): MainScreen