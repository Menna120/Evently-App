package com.route.onboarding.ui.onboarding_start

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.route.designsystem.navigation.Auth
import com.route.designsystem.utils.LocalRootNavController

@Composable
fun OnboardingStartScreen() {
    val navController = LocalRootNavController.current
    Button(onClick = { navController.navigate(Auth) }) {
        Text("Go to Auth Screen")
    }
}
