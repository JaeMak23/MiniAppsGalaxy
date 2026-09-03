package com.jaemak23.miniappsgalaxy.feature.auth.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.AuthRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.AuthScreen
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login.LoginScreen
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.signup.SignupScreen

@Composable
fun AuthNavigation(onLoginSuccessful: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, AuthRoute.Login)
    var isScreenLoading by remember { mutableStateOf(false) }

    AuthScreen(isScreenLoading) {

        NavDisplay(
            modifier = Modifier.fillMaxSize(),
            backStack = backStack,
            onBack = { backStack.goBack() }) { key ->
            when (key) {
                is AuthRoute.Login -> NavEntry(key) {
                    LoginScreen(
                        onLoadingChange = { isScreenLoading = it },
                        onLoginSuccess = onLoginSuccessful,
                        onSignupClick = { backStack.add(AuthRoute.Signup) }
                    )
                }

                is AuthRoute.Signup -> NavEntry(key) {
                    SignupScreen(
                        onLoadingChange = { isScreenLoading = it },
                        onLoginBackClick = { backStack.goBack() },
                        onAccountCreationSuccess = onLoginSuccessful
                    )
                }

                is AuthRoute.ForgotPassword -> NavEntry(key) { }
                else -> NavEntry(key) { }
            }
        }
    }
}