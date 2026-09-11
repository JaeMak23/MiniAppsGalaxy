package com.jaemak23.miniappsgalaxy.feature.appcatalog.di

import com.jaemak23.miniappsgalaxy.feature.appcatalog.presentation.AppsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val appsPresentationModule = module {
    viewModelOf(::AppsViewModel)
}

val appCatalogModule = module {
    includes(appsPresentationModule)
}