package com.jaemak23.miniappsgalaxy.core.domain.usecase.config

import com.jaemak23.miniappsgalaxy.core.domain.model.config.RemoteConfig
import com.jaemak23.miniappsgalaxy.core.domain.repository.RemoteConfigRepository

class GetRemoteConfigUseCase(private val remoteConfigRepository: RemoteConfigRepository) {
    suspend operator fun invoke(): RemoteConfig = remoteConfigRepository.getRemoteConfig()
}