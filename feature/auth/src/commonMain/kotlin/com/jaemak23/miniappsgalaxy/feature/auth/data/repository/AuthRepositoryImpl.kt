package com.jaemak23.miniappsgalaxy.feature.auth.data.repository

import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(username: String, password: String): Boolean {
        // Mock login
        return username == "admin" && password == "admin"
    }

    override suspend fun signup(username: String, password: String): Boolean {
        // Mock signup
        return true
    }

    override suspend fun forgotPassword(username: String): Boolean {
        return true
    }

    override suspend fun logout() {
        // Mock logout
    }
}