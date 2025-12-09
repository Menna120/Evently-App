package com.route.database

import com.route.model.Event
import com.route.model.Result
import kotlinx.coroutines.flow.Flow

interface EventLocalRepo {
    suspend fun getAllEvents(): Flow<Result<List<Event>>>
    suspend fun syncEvents(events: List<Event>): Flow<Result<Unit>>
    suspend fun getFavoriteEvents(): Flow<Result<List<Event>>>
    suspend fun getEvent(id: String): Flow<Result<Event>>
    suspend fun addEvent(event: Event): Flow<Result<Unit>>
    suspend fun searchEvents(query: String): Flow<Result<List<Event>>>
    suspend fun updateEvent(event: Event): Flow<Result<Unit>>
    suspend fun deleteEvent(event: Event): Flow<Result<Unit>>
}
