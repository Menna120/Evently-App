package com.route.network.event

import com.google.firebase.firestore.FirebaseFirestore
import com.route.model.Event
import com.route.network.EventRemoteRepo
import com.route.network.event.dto.EventDto
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class EventFireStoreRepo(db: FirebaseFirestore) : EventRemoteRepo {
    private val eventsCollection = db.collection("events")

    override suspend fun getEvents(): List<Event> =
        eventsCollection.get().await().toObjects(EventDto::class.java).map { it.toEvent() }

    override suspend fun addEvent(event: Event) {
        eventsCollection.add(event).await()
    }

    override suspend fun updateEvent(event: Event) {
        eventsCollection.document(event.id).set(event).await()
    }

    override suspend fun deleteEvent(event: Event) {
        eventsCollection.document(event.id).delete().await()
    }
}
