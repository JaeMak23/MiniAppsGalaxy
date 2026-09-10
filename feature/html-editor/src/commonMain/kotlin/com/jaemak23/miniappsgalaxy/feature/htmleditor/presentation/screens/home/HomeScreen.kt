package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.components.ExitButton
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    onExit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HTML Editor") },
                navigationIcon = {},
                actions = {
                    ThemeActionButton()
                    ExitButton { onExit() }
                }
            )
        },
        floatingActionButton = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    TooltipAnchorPosition.Above
                ),
                tooltip = { PlainTooltip { Text("Add New File") } },
                state = rememberTooltipState()
            ) {
                FloatingActionButton(onClick = { onAction(HomeAction.OnCreateBlankFileClick) }) {
                    Icon(
                        imageVector = AppIcons.Add,
                        contentDescription = "Add New File"
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            EmptyStatePlaceholder(
                isImporting = state.isImporting,
                onCreateBlankClick = { onAction(HomeAction.OnCreateBlankFileClick) },
                onImportClick = { onAction(HomeAction.OnImportFileClick) },
                modifier = Modifier.widthIn(max = 600.dp).heightIn(max = 400.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun EmptyStatePlaceholder(
    isImporting: Boolean,
    onCreateBlankClick: () -> Unit,
    onImportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = AppIcons.Add,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "No files yet",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Create a new file or open one from your device",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (isCompact) {
                Buttons(isImporting, onCreateBlankClick, onImportClick)

            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Buttons(isImporting, onCreateBlankClick, onImportClick)
                }
            }
        }
    }
}

@Composable
private fun Buttons(
    isImporting: Boolean,
    onCreateBlankClick: () -> Unit,
    onImportClick: () -> Unit
) {
    OutlinedButton(onClick = onCreateBlankClick) {
        Text("Create blank file")
    }
    Button(onClick = onImportClick, enabled = !isImporting) {
        if (isImporting) {
            CircularProgressIndicator(modifier = Modifier.size(16.dp))
        } else {
            Text("Open from device")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(state = HomeState(), onAction = {}, onExit = {})
}