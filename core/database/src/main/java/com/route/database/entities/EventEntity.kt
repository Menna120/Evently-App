package com.route.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.route.model.Event
import com.route.model.Location
import java.util.Date

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val date: Date,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean
) {
    fun toEvent(): Event = Event(
        id = id,
        title = title,
        description = description,
        type = type,
        date = date,
        location = Location(latitude, longitude),
        isFavorite = isFavorite
    )
}
