package com.route.evently.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

/**
 * Provides the root NavController for navigating between major graphs (Onboarding, Auth, Main).
 */
val LocalRootNavController =
    compositionLocalOf<NavHostController> { error("No Root NavController found!") }

/**
 * Provides the main NavController for navigating within the Main graph (e.g., bottom bar screens).
 */
val LocalMainNavController =
    compositionLocalOf<NavHostController> { error("No Main NavController found!") }
