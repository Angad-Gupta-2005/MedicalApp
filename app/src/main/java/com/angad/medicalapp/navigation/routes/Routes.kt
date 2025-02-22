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
}