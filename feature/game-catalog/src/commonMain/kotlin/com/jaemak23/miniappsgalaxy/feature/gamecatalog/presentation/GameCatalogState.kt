package com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation

import com.jaemak23.miniappsgalaxy.feature.gamecatalog.domain.model.GameCatalogItem

data class GameCatalogState(
    val games: List<GameCatalogItem> = emptyList(),
)