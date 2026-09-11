package com.jaemak23.miniappsgalaxy.core.ui.models

import androidx.compose.ui.graphics.vector.ImageVector

interface CatalogItem<T> {
    val id: T
    val title: String
    val description: String
    val icon: ImageVector
    val version: String
}