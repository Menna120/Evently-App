package com.route.evently.ui.main

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
import com.route.evently.navigation.main.MainDestination
import com.route.evently.navigation.main.bottomNavBarGraph
import com.route.evently.ui.main.composables.AddEventFab
import com.route.evently.ui.main.composables.EventlyBottomNavBar
import com.route.evently.ui.theme.EventlyTheme
import com.route.evently.utils.LocalMainNavController

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
                        currentDestination?.hierarchy?.any { it.route == destination::class.simpleName } == true
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
                    navController.navigate(MainDestination.CreateEventDestination)
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MainDestination.BottomNavDestination,
                modifier = Modifier.padding(innerPadding)
            ) {
                bottomNavBarGraph()
                composable<MainDestination.CreateEventDestination> { /* TODO */ }
                composable<MainDestination.EventDetailsDestination> { /* TODO */ }
                composable<MainDestination.UpdateEventDestination> { /* TODO */ }
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
