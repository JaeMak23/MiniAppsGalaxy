package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository
import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return authRepository.login(username, password).mapCatching { token->
                tokenRepository.saveToken(token)
            }
    }
}

