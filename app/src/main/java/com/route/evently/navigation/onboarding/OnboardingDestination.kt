package com.route.evently.navigation.onboarding

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnboardingDestination {

    @Serializable
    object Start : OnboardingDestination

    @Serializable
    object Onboarding : OnboardingDestination
}
