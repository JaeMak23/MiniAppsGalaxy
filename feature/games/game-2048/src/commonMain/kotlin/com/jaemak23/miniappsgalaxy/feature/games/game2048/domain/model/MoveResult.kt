package com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model

data class MoveResult(
        val board: Board,
        val scoreGained: Int,
        val moved: Boolean,
        val isGameOver: Boolean,
        val isWin: Boolean,
    )