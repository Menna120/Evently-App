package com.route.data

import com.route.database.EventLocalRepo
import com.route.model.Event
import com.route.network.EventRemoteRepo
import org.koin.core.annotation.Single

@Single
class EventManager(
    private val eventLocalRepo: EventLocalRepo,
    private val eventRemoteRepo: EventRemoteRepo
) {

    suspend fun getEvents(): List<Event> = eventLocalRepo.getAllEvents()

    suspend fun syncEvents() {
        eventRemoteRepo.getEvents().forEach {
            eventLocalRepo.addEvent(it)
        }
    }

    suspend fun getEvent(id: String): Event? = eventLocalRepo.getEvent(id)

    suspend fun addEvent(event: Event) {
        eventLocalRepo.addEvent(event)
        eventRemoteRepo.addEvent(event)
    }

    suspend fun searchEvents(query: String): List<Event> = eventLocalRepo.searchEvents(query)

    suspend fun getFavoriteEvents(): List<Event> = eventLocalRepo.getFavoriteEvents()

    suspend fun updateEvent(event: Event) {
        eventLocalRepo.updateEvent(event)
        eventRemoteRepo.updateEvent(event)
    }

    suspend fun deleteEvent(event: Event) {
        eventLocalRepo.deleteEvent(event)
        eventRemoteRepo.deleteEvent(event)
    }
}
