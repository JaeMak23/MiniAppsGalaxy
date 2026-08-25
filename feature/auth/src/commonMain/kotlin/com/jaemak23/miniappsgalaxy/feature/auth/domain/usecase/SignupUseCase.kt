package com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class SignupUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return repository.signup(username, password)
    }
}