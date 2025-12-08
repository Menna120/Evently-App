package com.route.datastore.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.route.datastore.TokenRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenDataStore(context: Context) : TokenRepo {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
    private val dataStore = context.dataStore

    private val tokenKey = stringPreferencesKey("token_key")

    override val token: Flow<String> = dataStore.data.map { preferences ->
        preferences[tokenKey] ?: ""
    }

    override suspend fun setToken(token: String) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                set(tokenKey, token)
            }
        }
    }
}
