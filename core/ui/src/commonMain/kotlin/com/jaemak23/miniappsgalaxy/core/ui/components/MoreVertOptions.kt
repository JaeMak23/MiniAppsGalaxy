package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

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