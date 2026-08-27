package com.jaemak23.miniappsgalaxy.feature.auth.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun signup(email: String, password: String): Result<String>
    suspend fun forgotPassword(email: String): Result<Unit>
}