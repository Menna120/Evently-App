package com.route.datastore

import com.route.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    val user: Flow<User>
    suspend fun setUser(user: User)
}
