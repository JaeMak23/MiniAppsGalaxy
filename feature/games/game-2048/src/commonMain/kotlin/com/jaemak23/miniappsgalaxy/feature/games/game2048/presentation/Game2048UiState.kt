package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Board

data class Game2048UiState(
    val board: Board = Board(),
    val score: Int = 0,
    val bestScore: Int = 0,
    val isGameOver: Boolean = false,
    val isWin: Boolean = false,
    val hasDismissedWin: Boolean = false,
)