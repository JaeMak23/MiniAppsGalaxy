package com.jaemak23.miniappsgalaxy.feature.htmleditor.di

import com.jaemak23.miniappsgalaxy.feature.htmleditor.domain.usecase.CreateBlankFileUseCase
import com.jaemak23.miniappsgalaxy.feature.htmleditor.domain.usecase.ImportHtmlFileUseCase
import com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val htmlEditorDomainModule = module {
    factoryOf(::CreateBlankFileUseCase)
    factoryOf(::ImportHtmlFileUseCase)
}

val htmlEditorPresentationModule = module {
    viewModelOf(::HomeViewModel)
}

val htmlEditorModule = module {
    includes(htmlEditorDomainModule, htmlEditorPresentationModule)
}