package com.route.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.route.model.User
import java.util.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val createdAt: Date,
    val eventsList: List<String>
) {
    fun toUser(): User = User(
        id = id,
        name = name,
        email = email,
        photoUrl = photoUrl,
        createdAt = createdAt,
        eventsList = eventsList
    )
}
