package com.jaemak23.miniappsgalaxy.feature.auth.di

import com.jaemak23.miniappsgalaxy.feature.auth.data.di.authDataModule
import com.jaemak23.miniappsgalaxy.feature.auth.domain.di.authDomainModule
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.di.authPresentationModule
import org.koin.dsl.module

val authModule = module {
    includes(authDataModule, authDomainModule, authPresentationModule)
}