package com.route.evently.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.evently.navigation.EventlyDestinations
import com.route.evently.ui.auth.forget_password.ForgetPasswordScreen
import com.route.evently.ui.auth.login.LoginScreen
import com.route.evently.ui.auth.register.RegisterScreen

fun NavGraphBuilder.authGraph() {
    navigation<EventlyDestinations.Auth>(startDestination = AuthDestination.Login) {
        composable<AuthDestination.Login> { LoginScreen() }
        composable<AuthDestination.Register> { RegisterScreen() }
        composable<AuthDestination.ForgetPassword> { ForgetPasswordScreen() }
    }
}
