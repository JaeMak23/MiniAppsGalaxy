package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Direction

sealed interface Game2048Action {
    data class Swipe(val direction: Direction) : Game2048Action
    data object Restart : Game2048Action
    data object DismissWinDialog : Game2048Action
}