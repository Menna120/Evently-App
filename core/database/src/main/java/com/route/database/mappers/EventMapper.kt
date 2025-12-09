package com.route.database.mappers

import com.route.database.entities.EventEntity
import com.route.model.Event

fun Event.toEntity() = EventEntity(
    id = id,
    title = title,
    description = description,
    type = type,
    timestamp = timestamp,
    latitude = location.latitude,
    longitude = location.longitude,
    favorite = favorite
)
