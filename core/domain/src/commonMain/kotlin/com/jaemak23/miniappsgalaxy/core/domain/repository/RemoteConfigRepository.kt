package com.jaemak23.miniappsgalaxy.core.domain.repository

import com.jaemak23.miniappsgalaxy.core.domain.model.config.RemoteConfig

interface RemoteConfigRepository {
    suspend fun getRemoteConfig(): RemoteConfig
}