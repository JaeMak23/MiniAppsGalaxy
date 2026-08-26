package com.jaemak23.miniappsgalaxy.core.data.di

import com.jaemak23.miniappsgalaxy.core.data.repository.InMemoryTokenRepository
import com.jaemak23.miniappsgalaxy.core.data.repository.MockRemoteConfigRepository
import com.jaemak23.miniappsgalaxy.core.domain.repository.RemoteConfigRepository
import com.jaemak23.miniappsgalaxy.core.domain.repository.TokenRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::InMemoryTokenRepository) { bind<TokenRepository>() }
    factoryOf(::MockRemoteConfigRepository) { bind<RemoteConfigRepository>() }
}