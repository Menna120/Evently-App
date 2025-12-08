package com.route.database.repo

import com.route.database.UserLocalRepo
import com.route.database.dao.UserDao
import com.route.database.mappers.toEntity
import com.route.model.User
import org.koin.core.annotation.Single

@Single
class UserRoomRepo(private val dao: UserDao) : UserLocalRepo {
    override suspend fun getUser(): User? = dao.getUser()?.toUser()

    override suspend fun addUser(user: User) {
        dao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.toEntity())
    }
}
