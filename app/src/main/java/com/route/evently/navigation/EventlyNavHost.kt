package com.route.evently.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.evently.navigation.auth.authGraph
import com.route.evently.navigation.onboarding.onboardingGraph
import com.route.evently.ui.main.MainScreen
import com.route.evently.utils.LocalRootNavController

@Composable
fun EventlyNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalRootNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = EventlyDestinations.Onboarding,
            modifier = modifier
        ) {
            onboardingGraph()
            authGraph()
            composable<EventlyDestinations.Main> { MainScreen() }
        }
    }
}
