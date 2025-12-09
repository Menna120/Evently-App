package com.route.model

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: Long,
    val location: Location,
    val type: String,
    val favorite: Boolean = false
)
