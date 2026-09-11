package com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation

import com.jaemak23.miniappsgalaxy.core.navigation.GameList

sealed interface GameCatalogAction {
    data class OnGameClick(val game: GameList) : GameCatalogAction
}