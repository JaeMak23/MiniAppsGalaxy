package com.jaemak23.miniappsgalaxy.feature.dashboard.di

import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.DashboardMainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dashboardModule = module {
    viewModelOf(::DashboardMainViewModel)
}
