package com.route.data

import com.route.datastore.SettingsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class SettingsManager(private val settingsRepo: SettingsRepo) {

    private val settingsScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val darkTheme: StateFlow<Boolean> = settingsRepo.darkTheme
        .stateIn(
            scope = settingsScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    val appLanguage: StateFlow<String> = settingsRepo.appLanguage
        .stateIn(
            scope = settingsScope,
            started = SharingStarted.Eagerly,
            initialValue = "en"
        )

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
