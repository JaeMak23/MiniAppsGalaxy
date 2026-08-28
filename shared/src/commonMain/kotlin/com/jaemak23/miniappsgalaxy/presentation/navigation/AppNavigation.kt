package com.jaemak23.miniappsgalaxy.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.AppRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.core.navigation.replaceRoute
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.navigation.AuthNavigation
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation.DashBoardNavigation
import com.jaemak23.miniappsgalaxy.feature.splash.presentation.SplashScreen

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(NavConfig, AppRoute.Splash)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.goBack() }
    ) { key ->

        when (key) {
            is AppRoute.Splash -> NavEntry(key) {
                SplashScreen { isLoggedIn ->
                    backStack.replaceRoute(if (isLoggedIn) AppRoute.Dashboard else AppRoute.Auth)
                }
            }

            is AppRoute.Auth -> NavEntry(key) {
                AuthNavigation {
                    backStack.replaceRoute(AppRoute.Dashboard)
                }
            }

            is AppRoute.Dashboard -> NavEntry(key) {
                DashBoardNavigation {
                    backStack.replaceRoute(AppRoute.Auth)
                }
            }

            else -> NavEntry(key) { }
        }
    }
}
