package com.jaemak23.miniappsgalaxy.feature.appcatalog.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.ui.models.CatalogItem

data class AppCatalogItem(
    override val id: AppList,
    override val title: String,
    override val description: String,
    override val icon: ImageVector,
    override val version: String
) : CatalogItem<AppList>