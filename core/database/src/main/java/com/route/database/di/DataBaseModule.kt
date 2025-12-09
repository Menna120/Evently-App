package com.route.database.di

import android.content.Context
import androidx.room.Room
import com.route.database.EventLocalRepo
import com.route.database.db.EventlyDatabase
import com.route.database.repo.EventRoomRepo
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.route.database")
class DataBaseModule {

    @Single
    fun provideDatabase(application: Context): EventlyDatabase =
        Room.databaseBuilder(application, EventlyDatabase::class.java, "evently.db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Single
    fun provideEventLocalRepo(db: EventlyDatabase): EventLocalRepo = EventRoomRepo(db.eventDao())

}
