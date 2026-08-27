package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.ShadowColumn
import com.jaemak23.miniappsgalaxy.core.ui.components.roundRectangleShadowStyle
import com.jaemak23.miniappsgalaxy.core.ui.extensions.maxReadableWidth
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.AuthLogoBox


@Composable
fun AuthInputSection(
    input: AuthInputBundle,
    modifier: Modifier,
    showLogo: Boolean = true,
    isCompact: Boolean = true
) {
    if (showLogo && !isCompact) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AuthLogoBox(Modifier.weight(1f).height(240.dp))
            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                AuthInputContainer { with(input) { Fields() } }
            }
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showLogo) {
                AuthLogoBox(Modifier.height(240.dp))
            }
            AuthInputContainer { with(input) { Fields() } }
        }
    }
}

@Composable
fun AuthInputContainer(content: @Composable (ColumnScope.() -> Unit)) {
    ShadowColumn(
        modifier = Modifier.maxReadableWidth(),
        style = roundRectangleShadowStyle(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content
    )
}