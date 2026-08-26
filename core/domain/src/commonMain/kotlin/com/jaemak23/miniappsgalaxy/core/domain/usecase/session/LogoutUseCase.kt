package com.jaemak23.miniappsgalaxy.core.domain.usecase.session

import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository

class LogoutUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke() {
        tokenRepository.clearToken()
    }
}