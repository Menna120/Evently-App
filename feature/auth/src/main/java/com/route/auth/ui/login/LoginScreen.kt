package com.route.auth.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.route.designsystem.navigation.Evently
import com.route.designsystem.utils.LocalRootNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = LocalRootNavController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            TextField(value = "", onValueChange = {}, placeholder = { Text("Email") })
            TextField(value = "", onValueChange = {}, placeholder = { Text("Password") })
            Button(onClick = { navController.navigate(Evently) }) {
                Text("Login")
            }
        }
        uiState.error?.let {
            Text(text = it)
        }
    }
}
