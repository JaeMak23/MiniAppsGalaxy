package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() {
        repository.logout()
    }
}