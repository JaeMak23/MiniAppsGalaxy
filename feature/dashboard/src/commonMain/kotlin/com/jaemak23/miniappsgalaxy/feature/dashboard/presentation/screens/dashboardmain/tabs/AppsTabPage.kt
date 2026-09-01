package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation.AppList

@Composable
fun AppsTabPage(onAppNav: (AppList) -> Unit) {
    Column(
        Modifier.fillMaxSize().safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Apps Dashboard Screen", style = MaterialTheme.typography.titleLarge)
        Button(onClick = { onAppNav(AppList.MarkdownNotes) }) {
            Text("Markdown Notes")
        }
    }
}