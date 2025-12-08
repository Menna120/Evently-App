package com.route.network

import com.route.model.User

interface UserRemoteRepo {
    suspend fun getUser(userId: String): User?
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}
