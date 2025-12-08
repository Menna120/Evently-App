package com.route.data

import com.route.database.UserLocalRepo
import com.route.model.User
import com.route.network.UserRemoteRepo
import org.koin.core.annotation.Single

@Single
class UserManager(
    private val userLocalRepo: UserLocalRepo,
    private val userRemoteRepo: UserRemoteRepo
) {
    suspend fun syncUser(userId: String) {
        userRemoteRepo.getUser(userId)?.let {
            userLocalRepo.addUser(it)
        }
    }

    suspend fun getUser(): User? = userLocalRepo.getUser()

    suspend fun updateUser(user: User) {
        userLocalRepo.updateUser(user)
        userRemoteRepo.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userLocalRepo.deleteUser(user)
        userRemoteRepo.deleteUser(user)
    }
}
