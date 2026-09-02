package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun NoteListScreen(
    state: NoteListState,
    onAction: (NoteListAction) -> Unit
) {
    var fabMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(onClick = { fabMenuExpanded = true }) {
                    Icon(AppIcons.Add, contentDescription = "Add note")
                }
                DropdownMenu(expanded = fabMenuExpanded, onDismissRequest = { fabMenuExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("New note") },
                        leadingIcon = { Icon(AppIcons.Add, contentDescription = null) },
                        onClick = { fabMenuExpanded = false; onAction(NoteListAction.OnNewClick) }
                    )
                    DropdownMenuItem(
                        text = { Text("Import from device") },
                        leadingIcon = { Icon(AppIcons.FileUpload, contentDescription = null) },
                        onClick = { fabMenuExpanded = false; onAction(NoteListAction.OnImportClick) }
                    )
                    DropdownMenuItem(
                        text = { Text("Open from device") },
                        leadingIcon = { Icon(AppIcons.FileOpen, contentDescription = null) },
                        onClick = { fabMenuExpanded = false; onAction(NoteListAction.OnOpenFromDeviceClick) }
                    )
                }
            }
        }
    ) { padding ->
        if (state.notes.isEmpty() && !state.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No notes yet — tap + to create, import, or open one")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.notes, key = { it.id }) { note ->
                NoteRow(
                    note = note,
                    onClick = { onAction(NoteListAction.OnNoteClick(note.id)) },
                    onDelete = { onAction(NoteListAction.OnDeleteNote(note.id)) }
                )
            }
        }
    }
}

@Composable
private fun NoteRow(note: NoteUi, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(note.title, fontWeight = FontWeight.SemiBold, maxLines = 1)
                Spacer(Modifier.height(4.dp))
                Text(note.preview, maxLines = 2, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(4.dp))
                Text(note.formattedDate, style = MaterialTheme.typography.labelSmall)
            }
            IconButton(onClick = onDelete) {
                Icon(AppIcons.Delete, contentDescription = "Delete")
            }
        }
    }
}