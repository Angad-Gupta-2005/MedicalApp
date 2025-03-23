package com.angad.medicalapp.navigation.appnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.screens.AddOrderScreen
import com.angad.medicalapp.screens.BottomNav
import com.angad.medicalapp.screens.GetAllProductScreen
import com.angad.medicalapp.screens.HomeScreen
import com.angad.medicalapp.screens.LoginScreen
import com.angad.medicalapp.screens.OrderHistoryScreen
import com.angad.medicalapp.screens.ProfileScreen
import com.angad.medicalapp.screens.SignUpScreen
import com.angad.medicalapp.screens.SpecificOrderScreen
import com.angad.medicalapp.screens.WaitingScreen
import com.angad.medicalapp.viewmodels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(viewModel: MyViewModel = hiltViewModel()) {

    val coroutineScope = rememberCoroutineScope()
    val userId = viewModel.userIdByPref.collectAsState()
    val navController = rememberNavController()
    val isUserLoggedIn = viewModel.getLoginStatusByPref.collectAsState()


    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getUserLoginStatus()
        }
    }

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getUserIdByPref()
        }
    }

    val startScreen = remember(userId.value) {
        if(userId.value.isNullOrEmpty()){
            Routes.SignUpScreenRoute
        } else {
            Routes.WaitingScreenRoute(userId = userId.value!!)
        }
    }


    NavHost(navController = navController, startDestination = if (isUserLoggedIn.value) Routes.BottomNavRoute else startScreen ) {

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
            HomeScreen(navController = navController)
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

//    For bottom navigation
        composable<Routes.BottomNavRoute> {
            BottomNav(userId = userId.value!!, navController = navController)
        }

    //    For order history
        composable<Routes.OrderHistoryRoute> {
            OrderHistoryScreen(userId = userId.value!!, navController = navController)
        }

    //    For Profile screen
        composable<Routes.ProfileRoute> {
            ProfileScreen()
        }

    //   For specific order screen
        composable<Routes.SpecificOrderRoute> {
            val orderId = it.arguments?.getString("orderId")?: ""
            SpecificOrderScreen(orderId = orderId, viewModel = viewModel)
        }


    }

}