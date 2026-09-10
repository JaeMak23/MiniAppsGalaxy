package com.jaemak23.miniappsgalaxy.feature.apps.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.navigation.AppList

data class AppCatalogItem(
    val id: AppList,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val version: String,
)