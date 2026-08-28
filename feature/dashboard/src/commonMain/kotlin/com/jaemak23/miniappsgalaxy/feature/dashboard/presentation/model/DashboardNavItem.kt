package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Immutable
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardTabRoute
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.DashboardMainUiState

@Immutable
data class DashboardNavItem(
    val tab: DashboardTabRoute,
    val label: String,
    val navIcon: NavIcon,
)

fun DashboardNavItem.toTab(uiState: DashboardMainUiState): TabItem {
    val selected = uiState.currentTab == tab
    val icon = if (selected) navIcon.selected else navIcon.unselected
    return TabItem(
        selected,
        { Icon(icon, contentDescription = label) },
        { Text(label) })
}