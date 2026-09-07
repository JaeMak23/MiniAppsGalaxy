package com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.signup

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalSnackbarHostState
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput.AuthInputBundle
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.AdaptiveContainer
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = koinViewModel(),
    onLoadingChange: (Boolean) -> Unit,
    onLoginBackClick: () -> Unit,
    onAccountCreationSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val retypePasswordState = rememberTextFieldState()
    val snackbarHostState = LocalSnackbarHostState.current

    LaunchedEffect(uiState.value) {
        onLoadingChange(uiState.value == SignupUiState.Loading)
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

    AdaptiveContainer(
        header = AuthCaptions.Signup,
        actions = AuthActions.Signup,
        input = AuthInputBundle.Signup(emailState, passwordState, retypePasswordState),
        onLinkClick = onLoginBackClick
    ) {
        viewModel.submit(
            emailState.text.toString(),
            passwordState.text.toString(),
            retypePasswordState.text.toString()
        )
    }
}