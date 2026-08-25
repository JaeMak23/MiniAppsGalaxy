package com.jaemak23.miniappsgalaxy.feature.auth.domain.di

import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.ForgotPasswordUseCase
import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.LoginUseCase
import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.LogoutUseCase
import com.jaemak23.miniappsgalaxy.feature.auth.domain.usecase.SignupUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::LoginUseCase)
    factoryOf(::SignupUseCase)
    factoryOf(::ForgotPasswordUseCase)
    factoryOf(::LogoutUseCase)
}