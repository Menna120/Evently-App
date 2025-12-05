package com.route.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.route.designsystem.navigation.Auth
import com.route.designsystem.utils.LocalRootNavController

@Composable
fun ProfileScreen() {
    val rootNavController = LocalRootNavController.current
    Button(onClick = { rootNavController.navigate(Auth) }) {
        Text("Logout")
    }
}
