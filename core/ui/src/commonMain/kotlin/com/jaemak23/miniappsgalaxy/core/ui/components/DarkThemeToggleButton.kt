package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview

@Composable
private fun BaseDarkThemeToggleButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.LightMode,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit = {}
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = "Toggle Dark Mode",
            tint = tint
        )
    }
}

@Composable
fun DarkThemeToggleButton(
    darkMode: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
) {
    val icon = if (darkMode) AppIcons.LightMode else AppIcons.DarkMode
    BaseDarkThemeToggleButton(modifier, icon) { onClick(!darkMode) }
}

@Composable
@Preview
private fun DarkThemeToggleButtonPreview() {
    ComponentPreview {
        DarkThemeToggleButton(darkMode = false, onClick = {})
    }
}
