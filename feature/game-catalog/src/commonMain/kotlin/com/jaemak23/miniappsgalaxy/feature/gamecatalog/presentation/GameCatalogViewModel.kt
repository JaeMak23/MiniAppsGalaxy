package com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.feature.gamecatalog.data.GameCatalog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class GameCatalogViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameCatalogState(games = GameCatalog.getGames()))
    val state = _state.asStateFlow()

    private val _events = Channel<GameCatalogEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: GameCatalogAction) {
        when (action) {
            is GameCatalogAction.OnGameClick -> {
                viewModelScope.launch {
                    _events.send(GameCatalogEvent.NavigateToApp(action.game))
                }
            }
        }
    }
}