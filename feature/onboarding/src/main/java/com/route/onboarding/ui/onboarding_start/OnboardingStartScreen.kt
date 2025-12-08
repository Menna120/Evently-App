package com.route.onboarding.ui.onboarding_start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.route.designsystem.navigation.Auth
import com.route.designsystem.utils.LocalRootNavController
import com.route.onboarding.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingStartScreen(viewModel: OnboardingStartViewModel = koinViewModel()) {
    val navController = LocalRootNavController.current
    val darkTheme by viewModel.darkTheme.collectAsState()
    val appLanguage by viewModel.appLanguage.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Theme", style = MaterialTheme.typography.bodyLarge)
            Switch(
                checked = darkTheme,
                onCheckedChange = viewModel::onThemeChanged
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.language), style = MaterialTheme.typography.bodyLarge)
            Switch(
                checked = appLanguage == "ar",
                onCheckedChange = viewModel::onLanguageChanged
            )
        }

        Button(onClick = { navController.navigate(Auth) }) {
            Text(stringResource(R.string.go_to_auth_screen))
        }
    }
}
