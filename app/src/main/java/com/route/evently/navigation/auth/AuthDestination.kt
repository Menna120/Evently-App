package com.route.evently.navigation.auth

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthDestination {

    @Serializable
    object Login : AuthDestination

    @Serializable
    object Register : AuthDestination

    @Serializable
    object ForgetPassword : AuthDestination
}
