package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.DeviceSize
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalDeviceWidth
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.extensions.maxReadableWidth
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputBundle
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.InputBlock
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions

@Composable
fun AdaptiveContainer(
    header: AuthCaptions,
    actions: AuthActions,
    input: AuthInputBundle,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    val device = LocalDeviceWidth.current

    Box(Modifier.fillMaxSize()) {
        when (device) {
            DeviceSize.DESKTOP, DeviceSize.TABLET ->
                WideAuthLayout(header, input, actions, device, onLinkClick, onButtonClick)

            DeviceSize.FOLDABLE -> CompactAuthLayout(
                header, input, actions, device,
                maxWidth = 600.dp,
                modifier = Modifier.align(Alignment.Center),
                onLinkClick = onLinkClick,
                onButtonClick = onButtonClick,
            )

            DeviceSize.PHONE -> CompactAuthLayout(
                header, input, actions, device,
                maxWidth = 480.dp,
                modifier = Modifier.align(Alignment.Center),
                onLinkClick = onLinkClick,
                onButtonClick = onButtonClick,
            )
        }

        ThemeActionButton(Modifier.align(Alignment.TopEnd).padding(16.dp))
    }
}

@Composable
private fun WideAuthLayout(
    header: AuthCaptions,
    input: AuthInputBundle,
    actions: AuthActions,
    device: DeviceSize,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Form(header, input, device)
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionBlock(actions, Modifier.maxReadableWidth(), onLinkClick, onButtonClick)
        }
    }
}

@Composable
private fun CompactAuthLayout(
    header: AuthCaptions,
    input: AuthInputBundle,
    actions: AuthActions,
    device: DeviceSize,
    maxWidth: Dp,
    modifier: Modifier,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Form(header, input, device, 120.dp, maxWidth)
        Spacer(Modifier.height(24.dp))
        ActionBlock(actions, Modifier.maxReadableWidth(), onLinkClick, onButtonClick)
    }
}

@Composable
private fun Form(
    header: AuthCaptions,
    input: AuthInputBundle,
    deviceSize: DeviceSize,
    authLogoSize: Dp = 180.dp,
    maxWidth: Dp = 720.dp
) {
    val showSideLogo = deviceSize != DeviceSize.PHONE

    HeaderBlock(
        header.heading,
        header.description,
        Modifier.maxReadableWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    Spacer(Modifier.height(32.dp))
    InputBlock(
        input = input,
        modifier = Modifier.maxReadableWidth(maxWidth),
        showSideLogo = showSideLogo,
        authLogoSize = authLogoSize
    )
}