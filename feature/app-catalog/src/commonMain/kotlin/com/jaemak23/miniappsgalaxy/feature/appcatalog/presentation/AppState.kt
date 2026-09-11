package com.jaemak23.miniappsgalaxy.feature.appcatalog.presentation

import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.feature.appcatalog.domain.model.AppCatalogItem

data class AppsState(
    val apps: List<AppCatalogItem> = emptyList(),
)

sealed interface AppsAction {
    data class OnAppClick(val app: AppList) : AppsAction
}

sealed interface AppsEvent {
    data class NavigateToApp(val app: AppList) : AppsEvent
}