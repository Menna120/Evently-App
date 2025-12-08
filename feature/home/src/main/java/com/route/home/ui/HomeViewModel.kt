package com.route.home.ui

import androidx.lifecycle.ViewModel
import com.route.data.SettingsManager
import kotlinx.coroutines.flow.StateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    val darkTheme: StateFlow<Boolean> = settingsManager.darkTheme
    val appLanguage: StateFlow<String> = settingsManager.appLanguage

    fun onThemeChanged(isDark: Boolean) {
        settingsManager.setTheme(isDark)
    }

    fun onLanguageChanged(isArabic: Boolean) {
        settingsManager.setLanguage(if (isArabic) "ar" else "en")
    }
}
