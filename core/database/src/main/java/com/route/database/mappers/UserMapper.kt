package com.route.database.mappers

import com.route.database.entities.UserEntity
import com.route.model.User

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    photoUrl = photoUrl,
    createdAt = createdAt,
    eventsList = eventsList
)
