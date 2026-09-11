package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Splash : AppRoute

    @Serializable
    data object Auth : AppRoute

    @Serializable
    data object Dashboard : AppRoute

}

@OptIn(ExperimentalSerializationApi::class)
val NavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclassesOfSealed<AppRoute>()
            subclassesOfSealed<AuthRoute>()
            subclassesOfSealed<DashboardRoute>()
            subclassesOfSealed<DashboardTabRoute>()
            subclassesOfSealed<MarkdownNotesRoute>()
            subclassesOfSealed<HtmlEditorRoute>()
            subclassesOfSealed<TicTacToeRoute>()
        }
    }
}
