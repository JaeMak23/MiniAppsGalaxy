package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class ForgotPasswordUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return authRepository.forgotPassword(email)
    }
}