package com.jaemak23.miniappsgalaxy.core.domain.di

import com.jaemak23.miniappsgalaxy.core.domain.usecase.config.GetRemoteConfigUseCase
import com.jaemak23.miniappsgalaxy.core.domain.usecase.session.IsLoggedInUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreDomainModule = module {
    factoryOf(::IsLoggedInUseCase)
    factoryOf(::GetRemoteConfigUseCase)
}