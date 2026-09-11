package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardTabRoute : NavKey {
    @Serializable
    data object Home : DashboardTabRoute

    @Serializable
    data object Apps : DashboardTabRoute

    @Serializable
    data object Games : DashboardTabRoute

    @Serializable
    data object Profile : DashboardTabRoute
}