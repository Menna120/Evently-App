package com.route.database.repo

import com.route.database.EventLocalRepo
import com.route.database.dao.EventDao
import com.route.database.mappers.toEntity
import com.route.model.Event
import org.koin.core.annotation.Single

@Single
class EventRoomRepo(private val dao: EventDao) : EventLocalRepo {
    override suspend fun getAllEvents(): List<Event> = dao.getAllEvents().map { it.toEvent() }
    override suspend fun getFavoriteEvents(): List<Event> =
        dao.getFavoriteEvents().map { it.toEvent() }

    override suspend fun getEvent(id: String): Event? = dao.getEvent(id)?.toEvent()

    override suspend fun addEvent(event: Event) {
        dao.insertEvent(event.toEntity())
    }

    override suspend fun searchEvents(query: String): List<Event> =
        dao.searchEvents(query).map { it.toEvent() }

    override suspend fun updateEvent(event: Event) {
        dao.updateEvent(event.toEntity())
    }

    override suspend fun deleteEvent(event: Event) {
        dao.deleteEvent(event.toEntity())
    }
}
