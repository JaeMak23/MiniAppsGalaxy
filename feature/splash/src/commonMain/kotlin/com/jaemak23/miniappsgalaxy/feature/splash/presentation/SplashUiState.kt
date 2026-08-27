package com.jaemak23.miniappsgalaxy.feature.splash.presentation

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data class Done(val isLoggedIn: Boolean) : SplashUiState
}
