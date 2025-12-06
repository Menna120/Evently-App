package com.route.evently.di

import com.route.auth.AuthModule
import com.route.data.DataModule
import com.route.event.EventModule
import com.route.favorites.FavoritesModule
import com.route.home.HomeModule
import com.route.map.MapModule
import com.route.onboarding.OnboardingModule
import com.route.profile.ProfileModule
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
