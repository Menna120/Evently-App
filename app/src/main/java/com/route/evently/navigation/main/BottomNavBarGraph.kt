package com.route.evently.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.route.evently.ui.main.screens.home.HomeScreen
import com.route.evently.ui.main.screens.love.LoveScreen
import com.route.evently.ui.main.screens.map.MapScreen
import com.route.evently.ui.main.screens.profile.ProfileScreen

fun NavGraphBuilder.bottomNavBarGraph() {
    navigation<MainDestination.BottomNavDestination>(
        startDestination = MainDestination.BottomItemDestination.Home
    ) {
        composable<MainDestination.BottomItemDestination.Home> { HomeScreen() }
        composable<MainDestination.BottomItemDestination.Map> { MapScreen() }
        composable<MainDestination.BottomItemDestination.Love> { LoveScreen() }
        composable<MainDestination.BottomItemDestination.Profile> { ProfileScreen() }
    }
}
