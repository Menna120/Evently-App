package com.route.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

sealed interface OnboardingState {
    data object Welcome : OnboardingState
    data object Pager : OnboardingState
}

@KoinViewModel
class OnboardingViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    private val _onboardingState = MutableStateFlow<OnboardingState>(OnboardingState.Welcome)
    val onboardingState: StateFlow<OnboardingState> = _onboardingState

    val isDarkTheme: StateFlow<Boolean> = settingsManager.darkTheme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )
    val isArabic: StateFlow<Boolean> = settingsManager.appLanguage
        .map { it == "ar" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun onThemeChanged(isDark: Boolean) {
        settingsManager.setTheme(isDark)
    }

    fun onLanguageChanged(isArabic: Boolean) {
        settingsManager.setLanguage(if (isArabic) "ar" else "en")
    }

    fun showPager() {
        _onboardingState.value = OnboardingState.Pager
    }

    fun onFinishOnboarding() {
        settingsManager.setShowOnboarding(false)
    }
}
