package com.jaemak23.miniappsgalaxy.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.SignupUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUseCase: SignupUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignupUiState>(SignupUiState.Idle)
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun submit(email: String, password: String, retypePassword: String) {
        if (password != retypePassword) {
            _uiState.value = SignupUiState.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _uiState.value = SignupUiState.Loading
            val result = signupUseCase(email, password)
            _uiState.value = result.fold(
                onSuccess = { SignupUiState.Success },
                onFailure = { SignupUiState.Error(it.message ?: "Signup failed") }
            )
        }
    }
}