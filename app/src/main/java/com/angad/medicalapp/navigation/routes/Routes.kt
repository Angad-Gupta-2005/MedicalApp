package com.angad.medicalapp.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object SignUpScreenRoute

    @Serializable
    data class WaitingScreenRoute(
        val userId: String
    )

    @Serializable
    object LoginScreenRoute

    @Serializable
    object HomeScreenRoute

    @Serializable
    object GetAllProductRoute

//    For AddOrderScreen
    @Serializable
    data class AddOrderRoute( val productId: String)

//    For bottom navigation
    @Serializable
    object BottomNavRoute

//    For OrderHistoryScreen
    @Serializable
    object OrderHistoryRoute

//    For ProfileScreen
    @Serializable
    object ProfileRoute

//    For specific order details
    @Serializable
    data class SpecificOrderRoute(val orderId: String)

//    For category screen
    @Serializable
    data class CategoryScreenRoute(val category: String)

}