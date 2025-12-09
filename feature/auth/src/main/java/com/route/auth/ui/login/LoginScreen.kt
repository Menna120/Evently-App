package com.route.auth.ui.login

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.route.designsystem.navigation.Evently
import com.route.designsystem.navigation.RegisterDestination
import com.route.designsystem.utils.LocalRootNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val appLanguage by viewModel.appLanguage.collectAsState()
    val darkTheme by viewModel.darkTheme.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val navController = LocalRootNavController.current
    val activity = LocalActivity.current

    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess) {
            navController.navigate(Evently)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Theme", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = darkTheme == true,
                    onCheckedChange = viewModel::onThemeChanged
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Language", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = appLanguage == "ar",
                    onCheckedChange = viewModel::onLanguageChanged
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                placeholder = { Text("Email") })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = { Text("Password") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = viewModel::signInWithEmailAndPassword) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.signInWithGoogle(activity) }) {
                Text("Sign in with Google")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { navController.navigate(RegisterDestination) }) {
                Text("Don't have an account? Sign up")
            }
        }
        uiState.error?.let {
            Text(text = it)
        }
    }
}
