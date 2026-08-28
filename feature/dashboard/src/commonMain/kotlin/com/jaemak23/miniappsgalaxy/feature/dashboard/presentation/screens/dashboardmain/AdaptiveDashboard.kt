package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailState
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardTabRoute
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model.toTab
import kotlinx.coroutines.launch

@Composable
fun AdaptiveDashboard(
    uiState: DashboardMainUiState,
    onCLick: (DashboardTabRoute) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    if (isCompact) {
        Scaffold(bottomBar = { CompactNavBar(uiState, onCLick) }) { content(it) }
    } else {

        val railState = rememberWideNavigationRailState()
        val scope = rememberCoroutineScope()
        val isExpanded = railState.targetValue == WideNavigationRailValue.Expanded

        Scaffold {
            Row(Modifier.fillMaxSize()) {
                CompactNavRail(uiState, onCLick, railState, isExpanded) {
                    scope.launch {
                        if (isExpanded) railState.collapse() else railState.expand()
                    }
                }
                VerticalDivider()
                content(it)
            }
        }
    }
}

@Composable
private fun CompactNavBar(uiState: DashboardMainUiState, onCLick: (DashboardTabRoute) -> Unit) {
    NavigationBar {
        uiState.dashboardTabs.forEach { item ->
            val tab = item.toTab(uiState)
            NavigationBarItem(
                selected = tab.selected,
                onClick = { onCLick(item.tab) },
                icon = tab.icon,
                label = tab.label
            )
        }
    }
}

@Composable
private fun CompactNavRail(
    uiState: DashboardMainUiState,
    onCLick: (DashboardTabRoute) -> Unit,
    railState: WideNavigationRailState,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit,
) {
    WideNavigationRail(
        state = railState,
        arrangement = Arrangement.spacedBy(8.dp),
        header = {
            IconButton(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .semantics {
                        stateDescription = if (isExpanded) "Expanded" else "Collapsed"
                    },
                onClick = onHeaderClick
            ) {
                Icon(
                    imageVector = if (isExpanded) AppIcons.ArrowBack else AppIcons.Menu,
                    contentDescription = if (isExpanded) "Collapse rail" else "Expand rail"
                )
            }
        }) {
        uiState.dashboardTabs.forEach { item ->
            val tab = item.toTab(uiState)
            WideNavigationRailItem(
                railExpanded = isExpanded,
                selected = tab.selected,
                onClick = { onCLick(item.tab) },
                icon = tab.icon,
                label = tab.label
            )
        }
    }
}