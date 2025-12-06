package com.route.evently.navigation

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.route.designsystem.composables.AddEventFab
import com.route.designsystem.composables.EventlyBottomNavBar
import com.route.designsystem.navigation.CreateEventDestination
import com.route.designsystem.navigation.EventDetailsDestination
import com.route.designsystem.navigation.EventlyBottomItems
import com.route.designsystem.navigation.UpdateEventDestination
import com.route.designsystem.theme.EventlyTheme
import com.route.designsystem.utils.LocalMainNavController
import com.route.event.event_details.EventDetailsScreen
import com.route.event.event_form.EventFormScreen
import com.route.favorites.FavoriteScreen
import com.route.home.HomeScreen
import com.route.map.MapScreen
import com.route.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalMainNavController provides navController) {
        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                EventlyBottomNavBar(
                    isSelected = { destination ->
                        currentDestination?.hierarchy?.any { it.route == destination::class.qualifiedName } == true
                    },
                    onItemClick = { destination ->
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            },
            floatingActionButton = {
                AddEventFab(
                    modifier = Modifier.offset { IntOffset(0, 46.dp.roundToPx()) }
                ) {
                    navController.navigate(CreateEventDestination)
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = EventlyBottomItems.Home,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<EventlyBottomItems.Home> { HomeScreen() }
                composable<EventlyBottomItems.Map> { MapScreen() }
                composable<EventlyBottomItems.Love> { FavoriteScreen() }
                composable<EventlyBottomItems.Profile> { ProfileScreen() }
                composable<EventDetailsDestination> { EventDetailsScreen() }
                composable<CreateEventDestination> { EventFormScreen() }
                composable<UpdateEventDestination> { EventFormScreen() }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MainScreenPreview() {
    EventlyTheme {
        MainScreen()
    }
}
