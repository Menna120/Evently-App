package com.route.evently

import android.app.Application
import com.route.evently.di.AppKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.ksp.generated.module

class EventlyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@EventlyApplication)
            modules(AppKoinModule().module)
        }
    }
}
