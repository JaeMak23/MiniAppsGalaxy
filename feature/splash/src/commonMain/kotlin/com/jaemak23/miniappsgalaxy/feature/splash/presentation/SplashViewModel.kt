package com.jaemak23.miniappsgalaxy.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.domain.usecase.GetRemoteConfigUseCase
import com.jaemak23.miniappsgalaxy.core.domain.usecase.IsLoggedInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getRemoteConfig: GetRemoteConfigUseCase,
    private val isLoggedIn: IsLoggedInUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getRemoteConfig()
            val loggedIn = isLoggedIn()
            _uiState.value = SplashUiState.Done(loggedIn)
        }
    }
}
