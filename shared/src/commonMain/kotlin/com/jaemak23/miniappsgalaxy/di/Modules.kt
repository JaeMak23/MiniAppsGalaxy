package com.jaemak23.miniappsgalaxy.di

import com.jaemak23.miniappsgalaxy.feature.auth.di.authModule
import com.jaemak23.miniappsgalaxy.feature.splash.di.splashModule
import org.koin.dsl.module

val appModule = module {
    includes(authModule, splashModule)
}