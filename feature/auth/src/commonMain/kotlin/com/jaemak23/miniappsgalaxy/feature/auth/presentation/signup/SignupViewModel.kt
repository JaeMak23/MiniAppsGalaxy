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

    fun signup(username: String, password: String, retypePassword: String) {
        if (password != retypePassword) {
            _uiState.value = SignupUiState.Error("Passwords do not match")
            return
        }
        viewModelScope.launch {
            _uiState.value = SignupUiState.Loading
            val success = signupUseCase(username, password)
            _uiState.value = if (success) {
                SignupUiState.Success
            } else {
                SignupUiState.Error("Signup failed. Try again.")
            }
        }
    }
}