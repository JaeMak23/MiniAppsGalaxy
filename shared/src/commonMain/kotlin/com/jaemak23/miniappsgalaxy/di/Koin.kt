package com.jaemak23.miniappsgalaxy.di

import com.jaemak23.miniappsgalaxy.core.di.initKoin as coreInitKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    coreInitKoin(
        modules = listOf(appModule),
        appDeclaration = appDeclaration
    )
}