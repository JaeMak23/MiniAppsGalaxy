package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.NavigationIcon
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.components.TooltipIconButton
import com.jaemak23.miniappsgalaxy.core.ui.components.panelBorder
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
                    Box(
                        modifier = Modifier.panelBorder()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        if (state.title.isBlank()) {
                            Text(
                                "Note Title",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                        BasicTextField(
                            value = state.title,
                            onValueChange = { onAction(NoteEditorAction.OnTitleChange(it)) },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                },
                navigationIcon = { NavigationIcon { onAction(NoteEditorAction.OnBackClick) } },
                actions = {
                    ActionRow(
                        isDraftMode = state.isDraftMode,
                        onSaveToListClick = { onAction(NoteEditorAction.OnSaveToListClick) },
                        onSaveToDeviceClick = { onAction(NoteEditorAction.OnSaveToDeviceClick) },
                        onExportClick = { onAction(NoteEditorAction.OnExportClick) },
                    )
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

@Composable
private fun ActionRow(
    isDraftMode: Boolean,
    onSaveToListClick: () -> Unit,
    onSaveToDeviceClick: () -> Unit,
    onExportClick: () -> Unit
) {
    val toNote = "Save to notes list"
    val toDevice = "Save to device"

    var menuExpanded by remember { mutableStateOf(false) }

    if (isDraftMode) {
        TooltipIconButton(toNote, onSaveToListClick) {
            Icon(AppIcons.Save, toNote)
        }
        TooltipIconButton(toDevice, onSaveToDeviceClick) {
            Icon(AppIcons.Import, toDevice)
        }

        ThemeActionButton()
        MoreVertOptions(menuExpanded, onAction = { menuExpanded = it }) {

        }

    } else {
        ThemeActionButton()
        MoreVertOptions(menuExpanded, onAction = { menuExpanded = it }) {
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(
                    text = { Text("Export to device") },
                    leadingIcon = {
                        Icon(
                            AppIcons.Import,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        menuExpanded = false
                        onExportClick()
                    }
                )
            }
        }
    }
}

@Composable
fun MoreVertOptions(
    menuExpanded: Boolean,
    onAction: (Boolean) -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Box {
        TooltipIconButton("More options", onClick = { onAction(true) }) {
            Icon(AppIcons.MoreVert, "More options")
        }
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { onAction(false) },
            content = content
        )
    }
}

/*
@Composable
fun ThemeActionButton() {
    var menuExpanded by remember { mutableStateOf(false) }
    val themeStr = "Change Theme flavors and theme-mode"
    val isDarkMode = LocalDarkMode.current

    Box {
        TooltipIconButton(themeStr, onClick = { menuExpanded = true }) {
            Icon(AppIcons.Theme, themeStr)
        }
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false }) {
            DropdownMenuItem(text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("DarkMode : ")
                    Switch(checked = isDarkMode.value, onCheckedChange = { isDarkMode.value = it })
                }
            }, onClick = {})
        }
    }
}*/
