package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalDarkMode
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview

@Composable
private fun BaseThemeBar(
    modifier: Modifier = Modifier,
    style: ShadowStyle = defaultShadowStyle(),
    onExpandClick: () -> Unit = {},
    toggleButton: @Composable () -> Unit = {}
) {
    ShadowRow(modifier = modifier, style = style) {
        toggleButton()
        IconButton(onClick = onExpandClick) {
            Icon(
                imageVector = AppIcons.ArrowDropDown,
                contentDescription = "Expand",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ThemeBar(
    modifier: Modifier = Modifier,
    onExpandClick: () -> Unit = {}
) {
    val darkMode = LocalDarkMode.current
    BaseThemeBar(
        modifier = modifier,
        onExpandClick = onExpandClick,
        toggleButton = {
            DarkThemeToggleButton(
                darkMode = darkMode.value,
                onClick = { darkMode.value = it }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ThemeBarPreview(){
    ComponentPreview {
        ThemeBar {  }
    }
}