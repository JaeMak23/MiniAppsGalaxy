package com.jaemak23.miniappsgalaxy.di

import com.jaemak23.miniappsgalaxy.core.data.di.coreDataModule
import com.jaemak23.miniappsgalaxy.core.domain.di.coreDomainModule
import com.jaemak23.miniappsgalaxy.feature.appcatalog.di.appCatalogModule
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.di.htmlEditorModule
import com.jaemak23.miniappsgalaxy.feature.auth.di.authModule
import com.jaemak23.miniappsgalaxy.feature.dashboard.di.dashboardModule
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.di.markdownNotesModule
import com.jaemak23.miniappsgalaxy.feature.gamecatalog.di.gameCatalogModule
import com.jaemak23.miniappsgalaxy.feature.splash.di.splashModule
import org.koin.dsl.module

val appModule = module {
    includes(
        coreDataModule,
        coreDomainModule,
        splashModule,
        authModule,
        dashboardModule,
        appCatalogModule,
        gameCatalogModule,
        markdownNotesModule,
        htmlEditorModule,
    )
}