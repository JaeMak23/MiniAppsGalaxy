package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface TicTacToeRoute : NavKey {

    @Serializable
    data object Home : TicTacToeRoute
}
