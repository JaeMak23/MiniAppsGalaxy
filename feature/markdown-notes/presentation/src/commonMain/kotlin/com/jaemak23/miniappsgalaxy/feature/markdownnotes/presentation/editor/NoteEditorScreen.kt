package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.NavigationIcon
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeBar
import com.jaemak23.miniappsgalaxy.core.ui.components.TooltipIconButton
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    state: NoteEditorState,
    onAction: (NoteEditorAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = { onAction(NoteEditorAction.OnTitleChange(it)) },
                        placeholder = { Text("Note Title") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                },
                navigationIcon = {
                    NavigationIcon(onClick = { onAction(NoteEditorAction.OnBackClick) })
                },
                actions = {
                    val toNote = "Save to notes list"
                    val toDevice = "Save to device"
                    if (state.isDraftMode) {
                        TooltipIconButton(
                            tooltip = toNote,
                            onClick = { onAction(NoteEditorAction.OnSaveToListClick) }
                        ) {
                            Icon(AppIcons.Save, contentDescription = toNote)
                        }
                        TooltipIconButton(
                            tooltip = toDevice,
                            onClick = { onAction(NoteEditorAction.OnSaveToDeviceClick) }
                        ) {
                            Icon(AppIcons.Import, contentDescription = toDevice)
                        }
                    }else{
                        var menuExpanded by remember { mutableStateOf(false) }
                        Box {
                            TooltipIconButton(tooltip = "More options", onClick = { menuExpanded = true }) {
                                Icon(AppIcons.MoreVert, contentDescription = "More options")
                            }
                            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Export to device") },
                                    leadingIcon = { Icon(AppIcons.Import, contentDescription = null) },
                                    onClick = { menuExpanded = false; onAction(NoteEditorAction.OnExportClick) }
                                )
                            }
                        }
                    }
                    ThemeBar()
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            NoteEditorLayout(
                state = state,
                onAction = onAction,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}