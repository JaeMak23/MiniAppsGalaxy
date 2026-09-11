package com.jaemak23.miniappsgalaxy.feature.gamecatalog.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.navigation.GameList
import com.jaemak23.miniappsgalaxy.core.ui.models.CatalogItem

data class GameCatalogItem(
    override val id: GameList,
    override val title: String,
    override val description: String,
    override val icon: ImageVector,
    override val version: String
) : CatalogItem<GameList>