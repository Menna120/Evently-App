package com.route.evently

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.designsystem.theme.EventlyTheme
import com.route.evently.navigation.EventlyApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashOn by mutableStateOf(true)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashOn }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme by viewModel.darkTheme.collectAsState()
            val appLanguage by viewModel.appLanguage.collectAsState()
            val showOnboarding by viewModel.showOnboarding.collectAsState()

            LaunchedEffect(darkTheme, appLanguage, showOnboarding) {
                if (darkTheme != null && appLanguage != null && showOnboarding != null) {
                    keepSplashOn = false
                }
            }

            LaunchedEffect(appLanguage) {
                appLanguage?.let {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(it))
                }
            }

            EventlyTheme(darkTheme = darkTheme == true || isSystemInDarkTheme()) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    showOnboarding?.let {
                        EventlyApp(
                            modifier = Modifier.padding(innerPadding),
                            showOnboarding = it
                        )
                    }
                }
            }
        }
    }
}
