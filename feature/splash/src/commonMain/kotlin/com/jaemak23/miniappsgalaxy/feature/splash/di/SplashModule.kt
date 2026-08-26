package com.jaemak23.miniappsgalaxy.feature.splash.di

import com.jaemak23.miniappsgalaxy.core.domain.usecase.GetRemoteConfigUseCase
import com.jaemak23.miniappsgalaxy.core.domain.usecase.IsLoggedInUseCase
import com.jaemak23.miniappsgalaxy.feature.splash.presentation.SplashViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    factoryOf(::GetRemoteConfigUseCase)
    factoryOf(::IsLoggedInUseCase)
    viewModelOf(::SplashViewModel)
}