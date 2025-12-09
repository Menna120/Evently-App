package com.route.data

import com.route.database.EventLocalRepo
import com.route.model.Event
import com.route.model.Result
import com.route.network.EventRemoteRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class EventManager(
    private val eventLocalRepo: EventLocalRepo,
    private val eventRemoteRepo: EventRemoteRepo
) {

    suspend fun getEvents(): Flow<Result<List<Event>>> = eventLocalRepo.getAllEvents()

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun syncEvents(): Flow<Result<Unit>> {
        return eventRemoteRepo.getEvents().flatMapConcat { result ->
            when (result) {
                is Result.Success -> eventLocalRepo.syncEvents(result.data)
                is Result.Error -> flowOf(Result.Error(result.message))
                Result.Loading -> flowOf(Result.Loading)
            }
        }
    }

    suspend fun getEvent(id: String): Flow<Result<Event>> = eventLocalRepo.getEvent(id)

    suspend fun addEvent(event: Event): Flow<Result<Unit>> = eventLocalRepo.addEvent(event)
        .combine(eventRemoteRepo.addEvent(event)) { localResult, remoteResult ->
            when {
                localResult is Result.Error -> Result.Error(localResult.message)
                remoteResult is Result.Error -> Result.Error(remoteResult.message)
                else -> Result.Success(Unit)
            }
        }

    suspend fun searchEvents(query: String): Flow<Result<List<Event>>> =
        eventLocalRepo.searchEvents(query)

    suspend fun getFavoriteEvents(): Flow<Result<List<Event>>> = eventLocalRepo.getFavoriteEvents()

    suspend fun updateEvent(event: Event): Flow<Result<Unit>> = eventLocalRepo.updateEvent(event)
        .combine(eventRemoteRepo.updateEvent(event)) { localResult, remoteResult ->
            when {
                localResult is Result.Error -> Result.Error(localResult.message)
                remoteResult is Result.Error -> Result.Error(remoteResult.message)
                else -> Result.Success(Unit)
            }
        }

    suspend fun deleteEvent(event: Event) =
        eventLocalRepo.deleteEvent(event)
            .combine(eventRemoteRepo.deleteEvent(event.id)) { localResult, remoteResult ->
                when {
                    localResult is Result.Error -> Result.Error(localResult.message)
                    remoteResult is Result.Error -> Result.Error(remoteResult.message)
                    else -> Result.Success(Unit)
                }
            }
}
