package com.route.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    val darkTheme: StateFlow<Boolean?> = settingsManager.darkTheme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )
    val appLanguage: StateFlow<String?> = settingsManager.appLanguage.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    fun onThemeChanged(isDark: Boolean) {
        settingsManager.setTheme(isDark)
    }

    fun onLanguageChanged(isArabic: Boolean) {
        settingsManager.setLanguage(if (isArabic) "ar" else "en")
    }
}
