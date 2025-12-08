package com.route.database.mappers

import com.route.database.entities.EventEntity
import com.route.model.Event

fun Event.toEntity() = EventEntity(
    id = id,
    title = title,
    description = description,
    type = type,
    date = date,
    latitude = location.latitude,
    longitude = location.longitude,
    isFavorite = isFavorite
)
