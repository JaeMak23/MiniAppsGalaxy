package com.jaemak23.miniappsgalaxy.feature.auth.data.di

import com.jaemak23.miniappsgalaxy.feature.auth.data.repository.AuthRepositoryImpl
import com.jaemak23.miniappsgalaxy.feature.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}