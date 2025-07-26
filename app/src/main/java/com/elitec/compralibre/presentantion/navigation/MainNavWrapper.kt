package com.elitec.compralibre.presentantion.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.elitec.compralibre.presentantion.navigation.home.HomeNavWrapper
import com.elitec.compralibre.presentantion.screen.LoginScreen
import com.elitec.compralibre.presentantion.screen.PassRecoveryScreen
import com.elitec.compralibre.presentantion.screen.PresentationScreen
import com.elitec.compralibre.presentantion.screen.RegisterScreen
import com.elitec.compralibre.presentantion.screen.SplashScreen
import com.elitec.compralibre.presentantion.viewmodels.AuthViewmodel

@Composable
fun MainNavWrapper(
    modifier: Modifier = Modifier
) {

    val authViewModel: AuthViewmodel = hiltViewModel()

    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Splash,
        enterTransition = { fadeIn(tween(200)) },
        exitTransition = { fadeOut(tween(200)) }
    ) {
        composable<Splash> {
            SplashScreen(
                navigateTo = { destination ->
                    navController.navigate(destination)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<Register> {
            RegisterScreen(
                authViewmodel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<Presentation> {
            PresentationScreen(
                onFinish = {
                    navController.navigate(Login)
                }
            )
        }
        composable<Login> {
            LoginScreen(
                navigateTo = { destination ->
                    navController.navigate(destination)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<PassRecovery> {
            PassRecoveryScreen(
                navigateTo = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<MainHome> { backStackEntry ->
            val userId = backStackEntry.toRoute<MainHome>().userId
            HomeNavWrapper(
                userId = userId,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}