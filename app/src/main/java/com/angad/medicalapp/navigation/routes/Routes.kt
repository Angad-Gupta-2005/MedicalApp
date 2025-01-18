package com.angad.medicalapp.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object SignUpScreenRoute

    @Serializable
    object WaitingScreenRoute
}