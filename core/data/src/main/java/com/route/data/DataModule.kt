package com.route.data

import com.route.database.DataBaseModule
import com.route.datastore.DataStoreModule
import com.route.network.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        DataBaseModule::class,
        DataStoreModule::class,
        NetworkModule::class
    ]
)
@ComponentScan
class DataModule
