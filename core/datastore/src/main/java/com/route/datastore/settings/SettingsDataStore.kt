package com.route.datastore.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.route.datastore.SettingsRepo
import com.route.datastore.settings.model.Settings
import com.route.datastore.settings.serializer.SettingsSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStore(context: Context) : SettingsRepo {
    private val Context.dataStore: DataStore<Settings> by dataStore(
        fileName = "settings.json",
        serializer = SettingsSerializer
    )
    private val dataStore = context.dataStore
    override val showOnboarding: Flow<Boolean> = dataStore.data.map { it.showOnboarding }
    override val darkTheme: Flow<Boolean> = dataStore.data.map { it.isDark }
    override val appLanguage: Flow<String> = dataStore.data.map { it.language }
    override suspend fun setShowOnboarding(show: Boolean) {
        dataStore.updateData {
            it.copy(showOnboarding = show)
        }
    }

    override suspend fun setTheme(isDark: Boolean) {
        dataStore.updateData {
            it.copy(isDark = isDark)
        }
    }

    override suspend fun setLanguage(language: String) {
        dataStore.updateData {
            it.copy(language = language)
        }
    }
}
