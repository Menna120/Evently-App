package com.route.data.di

import com.route.database.di.DataBaseModule
import com.route.datastore.di.DataStoreModule
import com.route.network.di.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        DataBaseModule::class,
        DataStoreModule::class,
        NetworkModule::class
    ]
)
@ComponentScan("com.route.data")
class DataModule
