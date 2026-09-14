package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Game2048Root(
    viewModel: Game2048ViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Game2048Screen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack,
    )
}