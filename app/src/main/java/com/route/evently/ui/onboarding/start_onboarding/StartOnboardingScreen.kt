package com.route.evently.ui.onboarding.start_onboarding

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.route.evently.navigation.EventlyDestinations
import com.route.evently.utils.LocalRootNavController

@Composable
fun StartOnboardingScreen() {
    val navController = LocalRootNavController.current
    Button(onClick = { navController.navigate(EventlyDestinations.Auth) }) {
        Text("Go to Auth Screen")
    }
}
