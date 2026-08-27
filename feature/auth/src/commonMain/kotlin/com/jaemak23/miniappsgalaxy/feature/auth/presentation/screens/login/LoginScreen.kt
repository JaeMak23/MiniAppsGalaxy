package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
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
    val snackbarHostState = remember { SnackbarHostState() }

    val header = AuthCaptions.Login
    val actions = AuthActions.Login
    val input = AuthInputBundle.Login(emailState, passwordState)

    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            is LoginUiState.Success -> {
                onLoginSuccess()
                viewModel.resetState()
            }

            is LoginUiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.resetState()
            }

            else -> Unit
        }
    }

    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().safeDrawingPadding()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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

            if (uiState.value == LoginUiState.Loading) {
                KLoadingOverlay()
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
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
fun KLoadingOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {}
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp,
            modifier = Modifier.size(96.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
