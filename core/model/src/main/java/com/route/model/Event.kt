package com.route.model

import java.util.Date

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val date: Date,
    val location: Location,
    val isFavorite: Boolean
)
