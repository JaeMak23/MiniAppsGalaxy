package com.jaemak23.miniappsgalaxy.feature.auth.data.repository

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(email: String, password: String): Result<String> {
        delay(2000.milliseconds)
        return if (email.isNotBlank() && password.isNotBlank()) {
            Result.success("mock-token-login-$email")
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun signup(email: String, password: String): Result<String> {
        delay(2000.milliseconds)
        return if (email.isNotBlank() && password.isNotBlank()) {
            Result.success("mock-token-signup-$email")
        } else {
            Result.failure(Exception("Signup failed"))
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit> {
        delay(2000.milliseconds)
        return if (email.isNotBlank()) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid email"))
        }
    }
}