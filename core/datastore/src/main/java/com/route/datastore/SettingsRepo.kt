package com.route.datastore

import kotlinx.coroutines.flow.Flow

interface SettingsRepo {
    val showOnboarding: Flow<Boolean>
    val darkTheme: Flow<Boolean>
    val appLanguage: Flow<String>
    suspend fun setShowOnboarding(show: Boolean)
    suspend fun setTheme(isDark: Boolean)
    suspend fun setLanguage(language: String)
}
