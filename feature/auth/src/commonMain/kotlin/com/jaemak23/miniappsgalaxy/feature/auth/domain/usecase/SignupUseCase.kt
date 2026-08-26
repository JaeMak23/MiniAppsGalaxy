package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository
import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class SignupUseCase(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.signup(email, password).mapCatching { token ->
            tokenRepository.saveToken(token)
        }
    }
}