package com.route.database

import com.route.model.Event

interface EventLocalRepo {
    suspend fun getAllEvents(): List<Event>
    suspend fun getFavoriteEvents(): List<Event>
    suspend fun getEvent(id: String): Event?
    suspend fun addEvent(event: Event)
    suspend fun searchEvents(query: String): List<Event>
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(event: Event)
}
