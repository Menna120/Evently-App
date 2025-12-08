package com.route.network

import com.route.model.Event

interface EventRemoteRepo {
    suspend fun getEvents(): List<Event>
    suspend fun addEvent(event: Event)
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(event: Event)
}
