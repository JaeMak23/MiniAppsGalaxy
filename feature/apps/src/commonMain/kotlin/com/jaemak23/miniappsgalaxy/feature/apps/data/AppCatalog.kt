package com.jaemak23.miniappsgalaxy.feature.apps.data

import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.apps.domain.model.AppCatalogItem

object AppCatalog {
    fun getApps(): List<AppCatalogItem> = listOf(
        AppCatalogItem(
            id = AppList.MarkdownNotes,
            title = "Markdown Notes",
            description = "Write and preview markdown notes",
            icon = AppIcons.FileMD,
        ),
        AppCatalogItem(
            id = AppList.HtmlEditor,
            title = "HTML Editor",
            description = "Edit and preview HTML snippets",
            icon = AppIcons.Code,
        ),
        AppCatalogItem(
            id = AppList.NoteEditor,
            title = "Note Editor",
            description = "Simple rich text notes",
            icon = AppIcons.Edit,
        ),
    )
}