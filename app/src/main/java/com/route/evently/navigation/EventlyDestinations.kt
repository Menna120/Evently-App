package com.route.evently.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface EventlyDestinations {

    @Serializable
    object Onboarding : EventlyDestinations

    @Serializable
    object Auth : EventlyDestinations

    @Serializable
    object Main : EventlyDestinations
}
