package com.route.data

import com.route.datastore.SettingsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class SettingsManager(private val settingsRepo: SettingsRepo) {

    private val settingsScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val showOnboarding: Flow<Boolean> = settingsRepo.showOnboarding

    val darkTheme: Flow<Boolean> = settingsRepo.darkTheme

    val appLanguage: Flow<String> = settingsRepo.appLanguage

    fun setShowOnboarding(show: Boolean) {
        settingsScope.launch {
            settingsRepo.setShowOnboarding(show)
        }
    }

    fun setTheme(isDark: Boolean) {
        settingsScope.launch {
            settingsRepo.setTheme(isDark)
        }
    }

    fun setLanguage(language: String) {
        settingsScope.launch {
            settingsRepo.setLanguage(language)
        }
    }
}
