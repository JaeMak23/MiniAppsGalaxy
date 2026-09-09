package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.home

sealed interface HomeAction {
    data object OnCreateBlankFileClick : HomeAction
    data object OnImportFileClick : HomeAction
}