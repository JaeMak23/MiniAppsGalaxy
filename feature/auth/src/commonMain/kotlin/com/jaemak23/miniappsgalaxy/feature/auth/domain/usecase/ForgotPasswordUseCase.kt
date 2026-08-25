package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class ForgotPasswordUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String): Boolean {
        return repository.forgotPassword(username)
    }
}