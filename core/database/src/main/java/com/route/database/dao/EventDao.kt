package com.route.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.route.database.entities.EventEntity

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventEntity>)

    @Query("SELECT * FROM events")
    suspend fun getAllEvents(): List<EventEntity>

    @Query("SELECT * FROM events WHERE favorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    @Query("SELECT * FROM events WHERE id= :id")
    suspend fun getEvent(id: String): EventEntity

    @Query("SELECT * FROM events WHERE favorite = 1 AND (title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%')")
    suspend fun searchEvents(query: String): List<EventEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: EventEntity)

    @Delete
    suspend fun deleteEvent(event: EventEntity)
}
