package com.route.auth.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.AuthManager
import com.route.data.SettingsManager
import com.route.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val authManager: AuthManager,
    private val settingsManager: SettingsManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
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

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun signInWithGoogle(context: Context) {
        authManager.signInWithGoogle(context).onEach { result ->
            when (result) {
                is Result.Error -> _uiState.update { it.copy(error = result.message) }
                Result.Loading -> Unit
                is Result.Success -> _uiState.update { it.copy(loginSuccess = true) }
            }
        }.launchIn(viewModelScope)
    }

    fun signInWithEmailAndPassword() {
        authManager.signInWithEmail(_uiState.value.email, _uiState.value.password)
            .onEach { result ->
                when (result) {
                    is Result.Error -> _uiState.update { it.copy(error = result.message) }
                    Result.Loading -> _uiState.update { it.copy(isLoading = result is Result.Loading) }
                    is Result.Success -> _uiState.update { it.copy(loginSuccess = true) }
                }
            }.launchIn(viewModelScope)
    }

    fun onLanguageChanged(isArabic: Boolean) {
        settingsManager.setLanguage(if (isArabic) "ar" else "en")
    }

    fun onThemeChanged(isDark: Boolean) = settingsManager.setTheme(isDark)
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    val loginSuccess: Boolean = false
)
