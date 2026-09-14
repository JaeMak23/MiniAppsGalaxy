package com.jaemak23.miniappsgalaxy.feature.games.game2048.di

import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.usecase.GameUseCase
import com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation.Game2048ViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import kotlin.random.Random

internal val game2048PresentationModule = module {
    factory{ GameUseCase(random = Random.Default,boardSize = 4) }
    viewModelOf(::Game2048ViewModel)
}

val game2048Module = module {
    includes(game2048PresentationModule)
}