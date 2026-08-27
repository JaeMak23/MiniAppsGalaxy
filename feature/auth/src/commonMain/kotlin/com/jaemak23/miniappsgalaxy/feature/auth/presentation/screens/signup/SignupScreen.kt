package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.signup

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeBar
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AuthActionBlock
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AuthHeader
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputSection
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputBundle
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login.KLoadingOverlay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = koinViewModel(),
    onLoginBackClick: () -> Unit,
    onAccountCreationSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val retypePasswordState = rememberTextFieldState()
    val snackbarHostState = remember { SnackbarHostState() }

    val header = AuthCaptions.Signup
    val actions = AuthActions.Signup
    val input = AuthInputBundle.Signup(emailState, passwordState, retypePasswordState)

    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            is SignupUiState.Success -> {
                onAccountCreationSuccess()
                viewModel.resetState()
            }

            is SignupUiState.Error -> {
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
                AuthActionBlock(actions, Modifier.fillMaxWidth(), onLinkClick = onLoginBackClick) {
                    viewModel.submit(
                        emailState.text.toString(),
                        passwordState.text.toString(),
                        retypePasswordState.text.toString()
                    )
                }

                Spacer(Modifier.height(32.dp))
            }
        }

            if (uiState.value == SignupUiState.Loading) {
                KLoadingOverlay()
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}