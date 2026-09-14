package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun Game2048Header(
    score: Int,
    bestScore: Int,
    onRestart: () -> Unit,
    onBack: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBack) {
            Icon(AppIcons.ArrowBack, contentDescription = "Back")
        }
        Spacer(Modifier.weight(1f))
        ScoreBadge(label = "Score", value = score)
        Spacer(Modifier.width(8.dp))
        ScoreBadge(label = "Best", value = bestScore)
        Spacer(Modifier.width(8.dp))
        IconButton(onClick = onRestart) {
            Icon(AppIcons.Refresh, contentDescription = "Restart")
        }
    }
}

@Composable
private fun ScoreBadge(label: String, value: Int) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(value.toString(), style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun WinDialog(
    onKeepPlaying: () -> Unit,
    onRestart: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onKeepPlaying,
        title = { Text("You win!") },
        text = { Text("You reached 2048. Keep going for a higher score?") },
        confirmButton = {
            TextButton(onClick = onKeepPlaying) { Text("Keep playing") }
        },
        dismissButton = {
            TextButton(onClick = onRestart) { Text("Restart") }
        },
    )
}