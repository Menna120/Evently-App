package com.route.network.mapper

import com.google.firebase.firestore.GeoPoint
import com.route.model.Event
import com.route.network.event.dto.FirebaseEvent

fun Event.toFirebaseEvent() = FirebaseEvent(
    id = id,
    title = title,
    description = description,
    timestamp = timestamp,
    location = location.let { GeoPoint(it.latitude, it.longitude) },
    type = type
)
