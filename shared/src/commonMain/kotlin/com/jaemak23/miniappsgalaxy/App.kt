package com.jaemak23.miniappsgalaxy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jaemak23.miniappsgalaxy.core.ui.AppContent
import com.jaemak23.miniappsgalaxy.presentation.navigation.AppNavigation

@Composable
@Preview
fun App() {
    AppContent { AppNavigation() }
}