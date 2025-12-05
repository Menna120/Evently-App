package com.route.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.designsystem.navigation.Onboarding
import com.route.designsystem.navigation.OnboardingPagesDestination
import com.route.designsystem.navigation.OnboardingStarDestination
import com.route.onboarding.ui.onboarding_pages.OnboardingPagesScreen
import com.route.onboarding.ui.onboarding_start.OnboardingStartScreen

fun NavGraphBuilder.onboardingGraph() {
    navigation<Onboarding>(startDestination = OnboardingStarDestination) {
        composable<OnboardingStarDestination> { OnboardingStartScreen() }
        composable<OnboardingPagesDestination> { OnboardingPagesScreen() }
    }
}
