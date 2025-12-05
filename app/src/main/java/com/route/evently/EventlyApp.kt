package com.route.evently

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.auth.navigation.authGraph
import com.route.designsystem.navigation.Evently
import com.route.designsystem.navigation.Onboarding
import com.route.designsystem.utils.LocalRootNavController
import com.route.onboarding.navigation.onboardingGraph

@Composable
fun EventlyNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalRootNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = Onboarding,
            modifier = modifier
        ) {
            onboardingGraph()
            authGraph()
            composable<Evently> { MainScreen() }
        }
    }
}
