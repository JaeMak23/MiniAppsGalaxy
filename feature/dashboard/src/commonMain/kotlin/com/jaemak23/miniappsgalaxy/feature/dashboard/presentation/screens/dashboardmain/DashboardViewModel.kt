package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.jaemak23.miniappsgalaxy.core.common.util.debugPrint
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardTabRoute
import com.jaemak23.miniappsgalaxy.core.navigation.switchTab
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model.DashboardNavItem
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model.NavIcon

class DashboardMainViewModel : ViewModel() {

    init {
        debugPrint("Dash-VM : Initialized")
    }

    val backStack = NavBackStack<NavKey>(DashboardTabRoute.Home)

    private val _dashboardNavItems = listOf(
        DashboardNavItem(
            DashboardTabRoute.Home,
            "Home",
            NavIcon(AppIcons.Home.Filled, AppIcons.Home.Outlined)
        ),
        DashboardNavItem(
            DashboardTabRoute.Apps,
            "Apps",
            NavIcon(AppIcons.Apps.Filled, AppIcons.Apps.Outlined)
        ),
        DashboardNavItem(
            DashboardTabRoute.Games,
            "Games",
            NavIcon(AppIcons.Games.Filled, AppIcons.Games.Outlined)
        ),
        DashboardNavItem(
            DashboardTabRoute.Profile,
            "Profile",
            NavIcon(AppIcons.Person.Filled, AppIcons.Person.Outlined)
        ),
    )

    val uiState: DashboardMainUiState
        get() = DashboardMainUiState(
            dashboardTabs = _dashboardNavItems,
            currentTab = (backStack.lastOrNull() as? DashboardTabRoute) ?: DashboardTabRoute.Home,
        )

    fun switchTab(tab: DashboardTabRoute) {
        backStack.switchTab(tab)
    }

    override fun onCleared() {
        super.onCleared()
        debugPrint("Dash-VM : Cleared")
    }
}