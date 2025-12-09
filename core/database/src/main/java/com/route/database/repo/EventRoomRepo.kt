package com.route.database.repo

import com.route.database.EventLocalRepo
import com.route.database.dao.EventDao
import com.route.database.mappers.toEntity
import com.route.database.util.MakeRoomCall.Companion.safeCall
import com.route.model.Event
import com.route.model.Result
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class EventRoomRepo(private val dao: EventDao) : EventLocalRepo {
    override suspend fun getAllEvents(): Flow<Result<List<Event>>> =
        safeCall { dao.getAllEvents().map { it.toEvent() } }

    override suspend fun syncEvents(events: List<Event>): Flow<Result<Unit>> =
        safeCall { dao.insertAll(events.map { it.toEntity() }) }

    override suspend fun getFavoriteEvents(): Flow<Result<List<Event>>> =
        safeCall { dao.getFavoriteEvents().map { it.toEvent() } }

    override suspend fun getEvent(id: String): Flow<Result<Event>> =
        safeCall { dao.getEvent(id).toEvent() }

    override suspend fun addEvent(event: Event): Flow<Result<Unit>> =
        safeCall { dao.insertEvent(event.toEntity()) }

    override suspend fun searchEvents(query: String): Flow<Result<List<Event>>> =
        safeCall { dao.searchEvents(query).map { it.toEvent() } }

    override suspend fun updateEvent(event: Event): Flow<Result<Unit>> =
        safeCall { dao.updateEvent(event.toEntity()) }

    override suspend fun deleteEvent(event: Event): Flow<Result<Unit>> =
        safeCall { dao.deleteEvent(event.toEntity()) }
}
