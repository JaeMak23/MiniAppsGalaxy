package com.jaemak23.miniappsgalaxy.core.domain.usecase.session

import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository

class IsLoggedInUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(): Boolean = tokenRepository.getToken() != null
}