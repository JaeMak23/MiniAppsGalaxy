package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.ShadowColumn
import com.jaemak23.miniappsgalaxy.core.ui.components.roundRectangleShadowStyle
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.AuthLogoBox

@Composable
fun InputBlock(
    input: AuthInputBundle,
    modifier: Modifier,
    showSideLogo: Boolean = false,
    authLogoSize: Dp = 180.dp,
) {

    if (showSideLogo) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AuthLogoBox(Modifier.size(authLogoSize))
            InputContainer(Modifier.weight(1f)) { with(input) { Fields() } }
        }
    } else {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthLogoBox(Modifier.size(authLogoSize))
            InputContainer(modifier) { with(input) { Fields() } }
        }
    }
}

@Composable
private fun InputContainer(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ShadowColumn(
        modifier = modifier,
        style = roundRectangleShadowStyle(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content
    )
}