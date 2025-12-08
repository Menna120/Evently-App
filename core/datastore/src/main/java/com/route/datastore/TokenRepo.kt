package com.route.datastore

import kotlinx.coroutines.flow.Flow

interface TokenRepo {
    val token: Flow<String>
    suspend fun setToken(token: String)
}
