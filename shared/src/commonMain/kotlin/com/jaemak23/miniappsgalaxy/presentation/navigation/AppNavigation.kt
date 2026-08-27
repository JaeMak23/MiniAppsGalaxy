package com.jaemak23.miniappsgalaxy.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.AppRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.core.navigation.replaceRoute
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.navigation.AuthNavigation
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
                    backStack.replaceRoute(if (isLoggedIn) AppRoute.DashBoard else AppRoute.Auth)
                }
            }

            is AppRoute.Auth -> NavEntry(key) {
                AuthNavigation {
                    backStack.replaceRoute(AppRoute.DashBoard)
                }
            }

            is AppRoute.DashBoard -> NavEntry(key) {
                DummyDashboard {
                    backStack.replaceRoute(AppRoute.Auth)
                }
            }

            else -> NavEntry(key) { }
        }
    }
}

@Composable
fun DummyDashboard(onLogout: () -> Unit) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().safeDrawingPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Dummy Dashboard Screen", style = MaterialTheme.typography.titleLarge)
            Button(onClick = onLogout) {
                Text("Logout")
            }
        }
    }
}
