package com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model

data class Board(
    val size: Int = 4,
    val tiles: List<Tile> = emptyList(),
) {
    fun tileAt(row: Int, col: Int): Tile? =
        tiles.find { it.row == row && it.col == col }
}