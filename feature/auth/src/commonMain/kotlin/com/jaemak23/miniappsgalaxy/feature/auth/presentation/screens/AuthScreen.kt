package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.KLoadingOverlay

@Composable
fun AuthScreen(
    isScreenLoading: Boolean,
    navContent: @Composable () -> Unit,
) {
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().safeDrawingPadding()) {
            navContent()

            if (isScreenLoading) {
                KLoadingOverlay()
            }
        }
    }
}
