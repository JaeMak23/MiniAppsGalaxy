package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HtmlEditorRoute : NavKey {
    @Serializable
    data object Home : HtmlEditorRoute

    @Serializable
    data class Editor(val title: String, val filePath: String?) : HtmlEditorRoute
}