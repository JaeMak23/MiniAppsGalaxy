package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun NavigationIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.ArrowBack,
    toolTip: String = "Back",
    contentDescription: String? = toolTip
) {
    TooltipIconButton(toolTip, onClick, modifier, TooltipAnchorPosition.Below) {
        Icon(icon, contentDescription = contentDescription)
    }
}