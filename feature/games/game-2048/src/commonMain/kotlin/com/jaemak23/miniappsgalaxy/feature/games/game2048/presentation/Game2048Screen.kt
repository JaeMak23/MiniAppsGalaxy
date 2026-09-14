package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Game2048Screen(
    state: Game2048UiState,
    onAction: (Game2048Action) -> Unit,
    onBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) {

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Game2048Header(
                    score = state.score,
                    bestScore = state.bestScore,
                    onRestart = { onAction(Game2048Action.Restart) },
                    onBack = onBack,
                )

                Spacer(Modifier.height(24.dp))

                BoardView(
                    board = state.board,
                    onSwipe = { direction -> onAction(Game2048Action.Swipe(direction)) },
                )

                if (state.isGameOver) {
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "Game over",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { onAction(Game2048Action.Restart) }) {
                        Text("Try again")
                    }
                }

                if (state.isWin && !state.hasDismissedWin) {
                    WinDialog(
                        onKeepPlaying = { onAction(Game2048Action.DismissWinDialog) },
                        onRestart = { onAction(Game2048Action.Restart) },
                    )
                }
            }
        }
    }
}