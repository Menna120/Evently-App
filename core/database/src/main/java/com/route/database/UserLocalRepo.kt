package com.route.database

import com.route.model.User

interface UserLocalRepo {
    suspend fun getUser(): User?
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}
