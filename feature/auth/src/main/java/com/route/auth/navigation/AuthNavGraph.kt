package com.route.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.auth.ui.forget_password.ForgetPasswordScreen
import com.route.auth.ui.login.LoginScreen
import com.route.auth.ui.register.RegisterScreen
import com.route.designsystem.navigation.Auth
import com.route.designsystem.navigation.ForgetPasswordDestination
import com.route.designsystem.navigation.LoginDestination
import com.route.designsystem.navigation.RegisterDestination

fun NavGraphBuilder.authGraph() {
    navigation<Auth>(startDestination = LoginDestination) {
        composable<LoginDestination> { LoginScreen() }
        composable<RegisterDestination> { RegisterScreen() }
        composable<ForgetPasswordDestination> { ForgetPasswordScreen() }
    }
}
