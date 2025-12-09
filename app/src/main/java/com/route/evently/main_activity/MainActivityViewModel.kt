package com.route.evently.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.AuthManager
import com.route.data.SettingsManager
import com.route.designsystem.navigation.Auth
import com.route.designsystem.navigation.Evently
import com.route.designsystem.navigation.Onboarding
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(
        val startDestination: Any,
        val isDarkTheme: Boolean?,
        val language: String
    ) : MainActivityUiState
}

@KoinViewModel
class MainActivityViewModel(
    settingsManager: SettingsManager,
    authManager: AuthManager
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = combine(
        authManager.getAuthState(),
        settingsManager.showOnboarding,
        settingsManager.darkTheme,
        settingsManager.appLanguage
    ) { user, showOnboarding, isDarkTheme, language ->
        MainActivityUiState.Success(
            isDarkTheme = isDarkTheme,
            language = language,
            startDestination = if (showOnboarding) Onboarding else if (user == null) Auth else Evently
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainActivityUiState.Loading
    )
}
