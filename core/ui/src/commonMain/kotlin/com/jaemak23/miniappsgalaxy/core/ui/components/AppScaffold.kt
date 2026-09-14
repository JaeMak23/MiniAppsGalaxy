package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    background: Brush? = null,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .then(if (background != null) Modifier.background(background) else Modifier),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = if (background != null) Color.Transparent
        else if (containerColor == Color.Unspecified) Color.Transparent
        else containerColor,
        contentColor = if (contentColor == Color.Unspecified) contentColorFor(MaterialTheme.colorScheme.background) else contentColor,
//        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = content,
    )
}