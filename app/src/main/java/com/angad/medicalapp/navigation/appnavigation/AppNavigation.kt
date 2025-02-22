package com.angad.medicalapp.navigation.appnavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.screens.AddOrderScreen
import com.angad.medicalapp.screens.GetAllProductScreen
import com.angad.medicalapp.screens.HomeScreen
import com.angad.medicalapp.screens.LoginScreen
import com.angad.medicalapp.screens.SignUpScreen
import com.angad.medicalapp.screens.WaitingScreen
import com.angad.medicalapp.viewmodels.MyViewModel


@Composable
fun AppNavigation(viewModel: MyViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LoginScreenRoute) {

        composable<Routes.SignUpScreenRoute> {
            SignUpScreen( navController = navController)
        }

        composable<Routes.WaitingScreenRoute> {
//            Getting the data from back screen
            val data = it.toRoute<Routes.WaitingScreenRoute>()
            WaitingScreen(
                navController = navController,
                userId = data.userId
            )
        }

        composable<Routes.LoginScreenRoute> {
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        composable<Routes.HomeScreenRoute> {
            HomeScreen()
        }

    //    For get allProduct
        composable<Routes.GetAllProductRoute> {
            GetAllProductScreen(navController = navController)
        }

//    For add order screen
        composable<Routes.AddOrderRoute> { backStack ->
           // val data = it.toRoute<Routes.AddOrderRoute>()
            val productId = backStack.arguments?.getString("productId")?: ""
            AddOrderScreen(
                productId = productId,
                viewModel = viewModel,
                navController = navController
            )
        }
    }

}