package com.jaemak23.miniappsgalaxy.core.di

import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    modules: List<Module>,
    appDeclaration: KoinAppDeclaration = {}
) {
    if (GlobalContext.getOrNull() == null) {
        startKoin {
            appDeclaration()
            modules(modules)
        }
    }
}