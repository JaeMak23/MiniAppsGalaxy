package com.jaemak23.miniappsgalaxy.core.data.repository

import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository

// mock impl in core:data for now
class InMemoryTokenRepository : TokenRepository {
    private var token: String? = null
    override suspend fun saveToken(token: String) { this.token = token }
    override suspend fun getToken(): String? = token
    override suspend fun clearToken() { token = null }
}
