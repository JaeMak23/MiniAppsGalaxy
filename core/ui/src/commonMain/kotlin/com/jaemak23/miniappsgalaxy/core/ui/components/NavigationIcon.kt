package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun NavigationIcon(
    onClick: () -> Unit,
    icon: ImageVector = AppIcons.ArrowBack,
    contentDescription: String? = "Back"
) {
    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = contentDescription)
    }
}