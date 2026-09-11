package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardRoute : NavKey {
    @Serializable
    data object DashBoard : DashboardRoute

    @Serializable
    data object MarkdownNotes : DashboardRoute

    @Serializable
    data object HtmlEditor : DashboardRoute

    @Serializable
    data object NoteEditor : DashboardRoute

    @Serializable
    data object TicTacToe : DashboardRoute

}