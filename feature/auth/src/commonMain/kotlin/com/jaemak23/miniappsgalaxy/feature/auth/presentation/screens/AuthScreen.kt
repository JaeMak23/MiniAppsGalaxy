package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.KLoadingOverlay

@Composable
fun AuthScreen(
    isScreenLoading: Boolean,
    snackbarHostState: SnackbarHostState,
    navContent: @Composable () -> Unit,
) {
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().safeDrawingPadding()) {
            navContent()

            if (isScreenLoading) {
                KLoadingOverlay()
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
