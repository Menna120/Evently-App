package com.route.evently.di

import com.route.auth.di.AuthModule
import com.route.data.di.DataModule
import com.route.event.di.EventModule
import com.route.favorites.di.FavoritesModule
import com.route.home.di.HomeModule
import com.route.map.di.MapModule
import com.route.onboarding.di.OnboardingModule
import com.route.profile.di.ProfileModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        DataModule::class,
        AuthModule::class,
        OnboardingModule::class,
        HomeModule::class,
        MapModule::class,
        FavoritesModule::class,
        ProfileModule::class,
        EventModule::class
    ]
)
@ComponentScan
class AppKoinModule
