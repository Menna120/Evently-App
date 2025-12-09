package com.route.evently

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainActivityViewModel(settingsManager: SettingsManager) : ViewModel() {

    val showOnboarding: StateFlow<Boolean?> = settingsManager.showOnboarding.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

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
}
