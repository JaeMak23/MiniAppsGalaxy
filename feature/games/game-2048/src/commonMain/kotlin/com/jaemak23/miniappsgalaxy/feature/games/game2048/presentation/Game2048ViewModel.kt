package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Direction
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.usecase.GameUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Game2048ViewModel(
    private val gameUseCase: GameUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(Game2048UiState())
    val state: StateFlow<Game2048UiState> = _state.asStateFlow()

    private val _events = Channel<Game2048Event>(Channel.BUFFERED)
    val events: Flow<Game2048Event> = _events.receiveAsFlow()

    init {
        startNewGame()
    }

    fun onAction(action: Game2048Action) {
        when (action) {
            is Game2048Action.Swipe -> handleSwipe(action.direction)
            is Game2048Action.Restart -> startNewGame()
            is Game2048Action.DismissWinDialog -> {
                _state.update { it.copy(hasDismissedWin = true) }
            }
        }
    }

    private fun handleSwipe(direction: Direction) {
        val current = _state.value
        if (current.isGameOver) return

        val result = gameUseCase.move(current.board, direction)
        if (!result.moved) return

        val newScore = current.score + result.scoreGained
        val newBest = maxOf(current.bestScore, newScore)
        val isNewHighScore = newBest > current.bestScore

        _state.update {
            it.copy(
                board = result.board,
                score = newScore,
                bestScore = newBest,
                isGameOver = result.isGameOver,
                isWin = result.isWin && !it.hasDismissedWin,
            )
        }

        if (result.isGameOver) {
            viewModelScope.launch { _events.send(Game2048Event.GameOver) }
        }
        if (isNewHighScore) {
            viewModelScope.launch { _events.send(Game2048Event.NewHighScore) }
        }
    }

    private fun startNewGame() {
        val bestScore = _state.value.bestScore
        _state.update {
            Game2048UiState(
                board = gameUseCase.initBoard(),
                bestScore = bestScore,
            )
        }
    }
}

