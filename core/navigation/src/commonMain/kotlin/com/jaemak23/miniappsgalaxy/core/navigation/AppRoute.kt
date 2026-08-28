package com.jaemak23.miniappsgalaxy.core.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface AppRoute : NavKey {

    @Serializable data object Splash : AppRoute

    @Serializable data object Auth : AppRoute

    @Serializable data object Dashboard : AppRoute

}

@Serializable
sealed interface AuthRoute : NavKey {

    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object Signup : AuthRoute

    @Serializable
    data object ForgotPassword : AuthRoute
}

@Serializable
sealed interface DashboardRoute : NavKey {

    @Serializable
    data object DashBoard : DashboardRoute
}

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

@OptIn(ExperimentalSerializationApi::class)
val NavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclassesOfSealed<AppRoute>()
            subclassesOfSealed<AuthRoute>()
            subclassesOfSealed<DashboardRoute>()
            subclassesOfSealed<DashboardTabRoute>()
        }
    }
}
