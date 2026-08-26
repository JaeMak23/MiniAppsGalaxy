package com.jaemak23.miniappsgalaxy.core.data.repository

import com.jaemak23.miniappsgalaxy.core.domain.model.config.RemoteConfig
import com.jaemak23.miniappsgalaxy.core.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MockRemoteConfigRepository : RemoteConfigRepository {
    override suspend fun getRemoteConfig(): RemoteConfig {
        delay(1000.milliseconds) // simulate network fetch
        return RemoteConfig(
            isForceUpdateRequired = false,
            isMaintenanceMode = false
        )
    }
}