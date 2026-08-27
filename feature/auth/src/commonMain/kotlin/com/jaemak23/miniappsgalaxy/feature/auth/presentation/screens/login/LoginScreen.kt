package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeBar
import com.jaemak23.miniappsgalaxy.core.ui.theme.AppPreview
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AuthActionBlock
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AuthHeader
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputSection
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputBundle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onSignupClick: () -> Unit,
    onLoginSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    val header = AuthCaptions.Login
    val actions = AuthActions.Login
    val input = AuthInputBundle.Login(emailState, passwordState)

    if (uiState.value == LoginUiState.Success) onLoginSuccess()

    Surface(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Box(Modifier.fillMaxWidth()) {
                    ThemeBar(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(bottom = 8.dp)
                    )
                }
            }
            item {
                Spacer(Modifier.height(32.dp))
                AuthHeader(header.heading, header.description, Modifier.fillMaxWidth())

                Spacer(Modifier.height(32.dp))
                AuthInputSection(input, Modifier.fillMaxWidth(), isCompact = isCompact)

                Spacer(Modifier.height(32.dp))
                AuthActionBlock(actions, Modifier.fillMaxWidth(), onLinkClick = onSignupClick) {
                    viewModel.submit(emailState.text.toString(), passwordState.text.toString())
                }

                Spacer(Modifier.height(32.dp))
            }
        }

        if (uiState.value == LoginUiState.Loading) LoadingBox()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppPreview {
        LoginScreen(onSignupClick = {}) { }
    }
}

@Composable
fun LoadingBox() {
    Box(
        Modifier.fillMaxSize()
            .safeDrawingPadding()
            .background(Color.Black.copy(alpha = 0.3f)), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(strokeWidth = 4.dp)
    }
}
