package com.jaemak23.miniappsgalaxy.feature.gamecatalog.di

import com.jaemak23.miniappsgalaxy.feature.gamecatalog.presentation.GameCatalogViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val gamesPresentationModule = module {
    viewModelOf(::GameCatalogViewModel)
}

val gameCatalogModule = module {
    includes(gamesPresentationModule)
}