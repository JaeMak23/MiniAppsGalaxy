package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.home

import com.jaemak23.miniappsgalaxy.core.ui.error.UiText
import com.jaemak23.miniappsgalaxy.feature.htmleditor.domain.model.HtmlFile

sealed interface HomeEvent {
    data class NavigateToEditor(val file: HtmlFile) : HomeEvent
    data class ShowSnackbar(val message: UiText) : HomeEvent
}