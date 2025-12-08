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
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.data.SettingsManager
import com.route.designsystem.theme.EventlyTheme
import com.route.evently.navigation.EventlyApp
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val settingsManager: SettingsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val darkTheme by settingsManager.darkTheme.collectAsState()
            val appLanguage by settingsManager.appLanguage.collectAsState()

            LaunchedEffect(appLanguage) {
                val localeList = LocaleListCompat.forLanguageTags(appLanguage)
                AppCompatDelegate.setApplicationLocales(localeList)
            }

            EventlyTheme(darkTheme = darkTheme || isSystemInDarkTheme()) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EventlyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
