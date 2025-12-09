package com.route.network.event

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.route.model.Event
import com.route.model.Result
import com.route.network.EventRemoteRepo
import com.route.network.event.dto.FirebaseEvent
import com.route.network.mapper.toFirebaseEvent
import com.route.network.util.MakeFirebaseCall.Companion.safeCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class FirebaseEventImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : EventRemoteRepo {

    private fun getEventCollection() = auth.currentUser?.uid?.let {
        firestore.collection("event_users").document(it).collection("event")
    }

    override suspend fun getEvents(): Flow<Result<List<Event>>> {
        val eventCollection = getEventCollection() ?: return flowOf(Result.Success(emptyList()))
        return safeCall {
            eventCollection.get().await().toObjects(FirebaseEvent::class.java).map { it.toEvent() }
        }
    }

    override suspend fun addEvent(event: Event): Flow<Result<Unit>> {
        val eventCollection = getEventCollection() ?: return flowOf(Result.Success(Unit))
        return safeCall { eventCollection.add(event.toFirebaseEvent()).await() }

    }

    override suspend fun updateEvent(event: Event): Flow<Result<Unit>> {
        val eventCollection = getEventCollection() ?: return flowOf(Result.Success(Unit))
        return safeCall { eventCollection.document(event.id).set(event.toFirebaseEvent()).await() }
    }

    override suspend fun deleteEvent(eventId: String): Flow<Result<Unit>> {
        val eventCollection = getEventCollection() ?: return flowOf(Result.Success(Unit))
        return safeCall { eventCollection.document(eventId).delete().await() }
    }
}
