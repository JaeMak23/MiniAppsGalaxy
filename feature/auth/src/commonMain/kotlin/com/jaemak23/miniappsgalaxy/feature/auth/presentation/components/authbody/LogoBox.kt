package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun AuthLogoBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHigh, CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = AppIcons.Logo,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}