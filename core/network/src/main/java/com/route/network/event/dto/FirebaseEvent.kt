package com.route.network.event.dto

import com.google.firebase.firestore.GeoPoint
import com.route.model.Event
import com.route.model.Location

data class FirebaseEvent(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val timestamp: Long = 0L,
    val location: GeoPoint = GeoPoint(0.0, 0.0),
    val type: String = "",
    val favorite: Boolean = false
) {
    fun toEvent(): Event = Event(
        id = id,
        title = title,
        description = description,
        timestamp = timestamp,
        location = Location(location.latitude, location.longitude),
        type = type,
        favorite = favorite
    )
}
