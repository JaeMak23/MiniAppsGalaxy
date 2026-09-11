package com.jaemak23.miniappsgalaxy.feature.appcatalog.data

import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.appcatalog.domain.model.AppCatalogItem

object AppCatalog {
    private const val DEFAULT_VERSION = "1.0.0"
    fun getApps(): List<AppCatalogItem> = listOf(
        AppCatalogItem(
            id = AppList.MarkdownNotes,
            title = "Markdown Notes",
            description = "Write and preview markdown notes",
            icon = AppIcons.FileMD,
            version = DEFAULT_VERSION,
        ),
        AppCatalogItem(
            id = AppList.HtmlEditor,
            title = "Basic HTML Editor",
            description = "Edit and preview HTML snippets",
            icon = AppIcons.Code,
            version = "0.1.0-alpha"
        ),
        AppCatalogItem(
            id = AppList.NoteEditor,
            title = "Note Editor",
            description = "Simple rich text notes",
            icon = AppIcons.Edit,
            version = DEFAULT_VERSION,
        ),
    )
}