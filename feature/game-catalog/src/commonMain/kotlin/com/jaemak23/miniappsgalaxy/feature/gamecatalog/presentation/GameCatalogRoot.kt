package com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.navigation.GameList
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameCatalogRoot(
    onGameNavigation: (GameList) -> Unit,
    viewModel: GameCatalogViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is GameCatalogEvent.NavigateToApp -> onGameNavigation(event.game)
        }
    }

    GameCatalogScreen(state = state, onAction = viewModel::onAction)
}