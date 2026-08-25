package com.jaemak23.miniappsgalaxy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jaemak23.miniappsgalaxy.presentation.navigation.AppNavigation

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}