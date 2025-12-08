package com.route.network.event.dto

import com.google.firebase.firestore.GeoPoint
import com.route.model.Event
import com.route.model.Location
import java.util.Date

data class EventDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val type: String = "",
    val date: Date = Date(),
    val location: GeoPoint = GeoPoint(0.0, 0.0),
    val isFavorite: Boolean = false
) {
    fun toEvent(): Event = Event(
        id = id,
        title = title,
        description = description,
        type = type,
        date = date,
        location = Location(location.latitude, location.longitude),
        isFavorite = isFavorite
    )
}
