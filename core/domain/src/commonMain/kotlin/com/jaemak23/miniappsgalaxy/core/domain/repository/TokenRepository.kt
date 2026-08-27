package com.jaemak23.miniappsgalaxy.core.domain.repository

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
}
