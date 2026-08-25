package com.jaemak23.miniappsgalaxy.feature.auth.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): Boolean
    suspend fun signup(username: String, password: String): Boolean
    suspend fun forgotPassword(username: String): Boolean
    suspend fun logout()
}