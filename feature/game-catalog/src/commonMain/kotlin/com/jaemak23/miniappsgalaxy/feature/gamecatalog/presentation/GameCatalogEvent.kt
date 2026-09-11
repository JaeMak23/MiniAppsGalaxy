package com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation

import com.jaemak23.miniappsgalaxy.core.navigation.GameList

sealed interface GameCatalogEvent {
    data class NavigateToApp(val game: GameList) : GameCatalogEvent
}