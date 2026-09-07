package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalSnackbarHostState
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AdaptiveContainer
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputBundle
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoadingChange: (Boolean) -> Unit,
    onSignupClick: () -> Unit,
    onLoginSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val snackbarHostState = LocalSnackbarHostState.current

    LaunchedEffect(uiState.value) {
        onLoadingChange(uiState.value == LoginUiState.Loading)
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

    AdaptiveContainer(
        header = AuthCaptions.Login,
        actions = AuthActions.Login,
        input = AuthInputBundle.Login(emailState, passwordState),
        onLinkClick = onSignupClick
    ) {
        viewModel.submit(emailState.text.toString(), passwordState.text.toString())
    }
}