package com.route.evently.main_activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.designsystem.theme.EventlyTheme
import com.route.evently.navigation.EventlyApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is MainActivityUiState.Loading
        }

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            when (val state = uiState) {
                is MainActivityUiState.Loading -> Unit

                is MainActivityUiState.Success -> {
                    LaunchedEffect(state.language) {
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(state.language)
                        )
                    }

                    EventlyTheme(
                        darkTheme = state.isDarkTheme ?: isSystemInDarkTheme()
                    ) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            EventlyApp(
                                modifier = Modifier.padding(innerPadding),
                                startDestination = state.startDestination
                            )
                        }
                    }
                }
            }
        }
    }
}
