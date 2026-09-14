package com.jaemak23.miniappsgalaxy.core.navigation.routes.games

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Game2048Route : NavKey {

    @Serializable
    data object Home : Game2048Route
}