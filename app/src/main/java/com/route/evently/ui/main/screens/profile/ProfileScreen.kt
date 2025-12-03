package com.route.evently.ui.main.screens.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.route.evently.navigation.EventlyDestinations
import com.route.evently.utils.LocalRootNavController

@Composable
fun ProfileScreen() {
    val rootNavController = LocalRootNavController.current
    Button(onClick = { rootNavController.navigate(EventlyDestinations.Auth) }) {
        Text("Logout")
    }
}
