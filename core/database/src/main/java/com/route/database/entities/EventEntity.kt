package com.route.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.route.model.Event
import com.route.model.Location

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double,
    val favorite: Boolean
) {
    fun toEvent(): Event = Event(
        id = id,
        title = title,
        description = description,
        type = type,
        timestamp = timestamp,
        location = Location(latitude, longitude),
        favorite = favorite
    )
}
