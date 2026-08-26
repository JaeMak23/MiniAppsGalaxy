package com.jaemak23.miniappsgalaxy.core.domain.usecase

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class GetRemoteConfigUseCase {
    suspend operator fun invoke(): RemoteConfig{
        delay(1000.milliseconds)
        return RemoteConfig(
            isForceUpdateRequired = false,
            isMaintenanceMode = false
        )
    }
}

data class RemoteConfig(
    val isForceUpdateRequired: Boolean,
    val isMaintenanceMode: Boolean
)
