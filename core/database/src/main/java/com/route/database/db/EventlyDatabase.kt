package com.route.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.database.dao.EventDao
import com.route.database.entities.EventEntity

@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EventlyDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
