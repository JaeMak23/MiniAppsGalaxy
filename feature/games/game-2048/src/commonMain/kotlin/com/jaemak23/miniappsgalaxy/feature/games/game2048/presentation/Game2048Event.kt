package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

sealed interface Game2048Event {
    data object GameOver : Game2048Event
    data object NewHighScore : Game2048Event
}