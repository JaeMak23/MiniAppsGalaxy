package com.jaemak23.miniappsgalaxy.feature.gamecatalog.data

import com.jaemak23.miniappsgalaxy.core.navigation.GameList
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.gamecatalog.domain.model.GameCatalogItem


object GameCatalog {
    private const val DEFAULT_VERSION = "1.0.0"
    fun getGames(): List<GameCatalogItem> = listOf(
        GameCatalogItem(
            id = GameList.TicTacToe,
            title = "Tic-Tac-Toe",
            description = "Play Tic-Tac-Toe against the bot",
            icon = AppIcons.Email,
            version = DEFAULT_VERSION
        ),
    )
}