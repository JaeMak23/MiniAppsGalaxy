package com.jaemak23.miniappsgalaxy.core.ui.components.gamecomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.ExitButton
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.components.TooltipIconButton
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
private fun GameTopBar(
    title: String,
    showBackNavigationButton: Boolean,
    showExitButton: Boolean,
    showThemeButton: Boolean = true,
    onBack: (() -> Unit) = {},
    onExit: (() -> Unit) = {},
    endContent: (@Composable () -> Unit)? = null
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (showBackNavigationButton)
            TooltipIconButton("Back", onClick = onBack) {
                Icon(AppIcons.ArrowBack, contentDescription = "Back")
            }

        Text(
            title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(Modifier.weight(1f))

        endContent?.let { endContent() }

        if (showThemeButton)
            ThemeActionButton()

        if (showExitButton)
            ExitButton(onExit = onExit)
    }
}

@Composable
fun GameTopBarWithExit(
    title: String,
    onExit: () -> Unit,
    showThemeButton: Boolean = true,
    endContent: (@Composable () -> Unit)? = null
) {
    GameTopBar(
        title = title,
        showBackNavigationButton = false,
        showExitButton = true,
        onExit = onExit,
        showThemeButton = showThemeButton,
        endContent = endContent
    )
}

@Composable
fun GameTopBarWithBack(
    title: String,
    onBack: () -> Unit,
    showThemeButton: Boolean = true,
    endContent: (@Composable () -> Unit)? = null
) {
    GameTopBar(
        title = title,
        showBackNavigationButton = true,
        showExitButton = false,
        onBack = onBack,
        showThemeButton = showThemeButton,
        endContent = endContent
    )
}