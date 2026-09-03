package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No LocalSnackbarHostState provided")
}