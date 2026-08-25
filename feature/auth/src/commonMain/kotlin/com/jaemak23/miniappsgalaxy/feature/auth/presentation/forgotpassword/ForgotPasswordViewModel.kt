package com.jaemak23.miniappsgalaxy.feature.auth.presentation.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.ForgotPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ForgotPasswordUiState>(ForgotPasswordUiState.Idle)
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState.asStateFlow()

    fun submit(username: String) {
        viewModelScope.launch {
            _uiState.value = ForgotPasswordUiState.Loading
            val success = forgotPasswordUseCase(username)
            _uiState.value = if (success) {
                ForgotPasswordUiState.Success
            } else {
                ForgotPasswordUiState.Error("Could not send reset instructions")
            }
        }
    }
}