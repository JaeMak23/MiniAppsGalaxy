package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain

import com.jaemak23.miniappsgalaxy.core.navigation.DashboardTabRoute
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model.DashboardNavItem

data class DashboardMainUiState(
    val dashboardTabs: List<DashboardNavItem> = emptyList(),
    val currentTab: DashboardTabRoute = DashboardTabRoute.Home,
)