package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import com.jaemak23.miniappsgalaxy.core.common.util.debugPrint
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation.DashboardTabNavigation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    onLogout: () -> Unit,
    viewModel: DashboardMainViewModel = koinViewModel(),
) {
    val backStack = viewModel.backStack
    val uiState = viewModel.uiState

    LaunchedEffect(Unit){
        debugPrint("Dashboard-Screen : Launched")
    }

    AdaptiveDashboard(
        uiState = uiState,
        onCLick = { viewModel.switchTab(it) },
        content = { paddingValues ->
            DashboardTabNavigation(
                backStack=backStack,
                paddingValues= paddingValues,
                onLogout = onLogout
            )
        })

    DisposableEffect(Unit){
        onDispose {
            debugPrint("Dashboard-Screen : Disposed")
        }
    }
}