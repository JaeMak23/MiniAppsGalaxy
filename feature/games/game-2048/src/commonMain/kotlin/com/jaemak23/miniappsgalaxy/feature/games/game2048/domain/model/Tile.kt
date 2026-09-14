package com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model

data class Tile(
    val id: Long,
    val value: Int,
    val row: Int,
    val col: Int,
    val isNew: Boolean = false,
    val isMerged: Boolean = false,
)