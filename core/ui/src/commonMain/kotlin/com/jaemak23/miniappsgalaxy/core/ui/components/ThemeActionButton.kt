package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalDarkMode
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalThemeFlavor
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.core.ui.theme.ThemeManager

@Composable
private fun ThemeActionIconButton(modifier: Modifier = Modifier) {
    var menuExpanded by remember { mutableStateOf(false) }
    val themeStr = "Change theme flavor and mode"
    val isDarkMode = LocalDarkMode.current
    val themeFlavor = LocalThemeFlavor.current

    Box(modifier) {
        TooltipIconButton(themeStr, onClick = { menuExpanded = true }) {
            Icon(AppIcons.Theme, themeStr)
        }
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false }
        ) {
            Text(
                "Theme mode: ",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            DropdownMenuItem(
                text = { Text("${if (isDarkMode.value) "Disable" else "Enabled"} Dark Mode") },
                leadingIcon = {
                    Icon(
                        if (isDarkMode.value) AppIcons.DarkMode else AppIcons.LightMode,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Switch(
                        checked = isDarkMode.value,
                        onCheckedChange = { isDarkMode.value = it }
                    )
                },
                onClick = { isDarkMode.value = !isDarkMode.value }
            )

            HorizontalDivider()

            Text(
                "Theme flavor",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            ThemeManager.entries.forEach { theme ->
                DropdownMenuItem(
                    text = { Text(theme.name) },
                    leadingIcon = {
                        if (theme == themeFlavor.value) {
                            Icon(AppIcons.Check, contentDescription = null)
                        }
                    },
                    onClick = {
                        themeFlavor.value = theme
                        menuExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeActionButton(modifier: Modifier = Modifier) {
    Box(modifier) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Below),
            tooltip = { PlainTooltip { Text("Change theme flavor and mode") } },
            state = rememberTooltipState()
        ) { ThemeActionIconButton() }
    }
}