package com.route.datastore.di

import android.content.Context
import com.route.datastore.SettingsRepo
import com.route.datastore.UserRepo
import com.route.datastore.settings.SettingsDataStore
import com.route.datastore.user.UserDataStore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.route.datastore")
class DataStoreModule {

    @Single
    fun provideSettingsRepo(context: Context): SettingsRepo = SettingsDataStore(context)

    @Single
    fun provideUserRepo(context: Context): UserRepo = UserDataStore(context)
}
