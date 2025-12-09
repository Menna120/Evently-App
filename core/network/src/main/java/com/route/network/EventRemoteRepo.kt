package com.route.network

import com.route.model.Event
import com.route.model.Result
import kotlinx.coroutines.flow.Flow

interface EventRemoteRepo {
    suspend fun getEvents(): Flow<Result<List<Event>>>
    suspend fun addEvent(event: Event): Flow<Result<Unit>>
    suspend fun updateEvent(event: Event): Flow<Result<Unit>>
    suspend fun deleteEvent(eventId: String): Flow<Result<Unit>>
}
