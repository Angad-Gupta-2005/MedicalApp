package com.angad.medicalapp.navigation.appnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.screens.SignUpScreen
import com.angad.medicalapp.screens.WaitingScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SignUpScreenRoute) {

        composable<Routes.SignUpScreenRoute> {
            SignUpScreen( navController = navController)
        }

        composable<Routes.WaitingScreenRoute> {
            WaitingScreen()
        }
    }
}