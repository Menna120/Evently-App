package com.route.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.database.dao.EventDao
import com.route.database.dao.UserDao
import com.route.database.entities.EventEntity
import com.route.database.entities.UserEntity

@Database(
    entities = [EventEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EventlyDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun userDao(): UserDao
}
