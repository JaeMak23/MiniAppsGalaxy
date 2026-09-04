package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun NoteListFab(
    onNewClick: () -> Unit,
    onImportClick: () -> Unit,
    onOpenClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.End) {
        SpeedDialItem(
            visible = expanded,
            label = "Open from device",
            icon = AppIcons.FileOpen,
            onClick = { expanded = false; onOpenClick() }
        )
        Spacer(Modifier.height(8.dp))
        SpeedDialItem(
            visible = expanded,
            label = "Import from device",
            icon = AppIcons.FileUpload,
            onClick = { expanded = false; onImportClick() }
        )
        Spacer(Modifier.height(8.dp))
        SpeedDialItem(
            visible = expanded,
            label = "New note",
            icon = AppIcons.Add,
            onClick = { expanded = false; onNewClick() }
        )
        Spacer(Modifier.height(8.dp))
        FloatingActionButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) AppIcons.Close else AppIcons.Add,
                contentDescription = if (expanded) "Close menu" else "Add"
            )
        }
    }
}

@Composable
private fun SpeedDialItem(
    visible: Boolean,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                tonalElevation = 2.dp
            ) {
                Text(label, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
            }
            Spacer(Modifier.width(12.dp))
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier.size(40.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(icon, contentDescription = label, modifier = Modifier.size(20.dp))
            }
        }
    }
}