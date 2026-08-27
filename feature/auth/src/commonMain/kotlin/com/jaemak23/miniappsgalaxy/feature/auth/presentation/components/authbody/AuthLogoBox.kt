package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun AuthLogoBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    size: Dp = 180.dp
) {
    Box(modifier, contentAlignment) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = AppIcons.Logo,
                contentDescription = "App Logo",
                modifier = Modifier.fillMaxSize(),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}