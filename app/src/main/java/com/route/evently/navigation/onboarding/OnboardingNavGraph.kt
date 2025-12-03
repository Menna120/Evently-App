package com.route.evently.navigation.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.evently.navigation.EventlyDestinations
import com.route.evently.ui.onboarding.onboarding_pager.PagerOnboardingScreen
import com.route.evently.ui.onboarding.start_onboarding.StartOnboardingScreen

fun NavGraphBuilder.onboardingGraph() {
    navigation<EventlyDestinations.Onboarding>(startDestination = OnboardingDestination.Start) {
        composable<OnboardingDestination.Start> { StartOnboardingScreen() }
        composable<OnboardingDestination.Onboarding> { PagerOnboardingScreen() }
    }
}
