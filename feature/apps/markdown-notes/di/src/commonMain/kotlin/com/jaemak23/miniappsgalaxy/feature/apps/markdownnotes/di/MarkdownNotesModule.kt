package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.di

import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data.InMemoryDraftDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data.InMemoryNoteDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.DraftDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.NoteLocalDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.ClearDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.DeleteNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.ExportNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.GetDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.GetNoteByIdUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.GetNotesUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.ImportNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.OpenFileAsDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.SaveDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase.SaveNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.presentation.editor.NoteEditorViewModel
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.presentation.list.NoteListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val markdownNotesDataModule = module {
    singleOf(::InMemoryNoteDataSource) bind NoteLocalDataSource::class
    singleOf(::InMemoryDraftDataSource) bind DraftDataSource::class
}

val markdownNotesDomainModule = module {
    factoryOf(::GetNotesUseCase)
    factoryOf(::GetNoteByIdUseCase)
    factoryOf(::SaveNoteUseCase)
    factoryOf(::DeleteNoteUseCase)
    factoryOf(::ImportNoteUseCase)
    factoryOf(::OpenFileAsDraftUseCase)
    factoryOf(::SaveDraftUseCase)
    factoryOf(::GetDraftUseCase)
    factoryOf(::ClearDraftUseCase)
    factoryOf(::ExportNoteUseCase)
}

val markdownNotesPresentationModule = module {
    viewModelOf(::NoteListViewModel)
    viewModel { params ->
        NoteEditorViewModel(
            params.get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}

val markdownNotesModule = module {
    includes(
        markdownNotesDataModule,
        markdownNotesDomainModule,
        markdownNotesPresentationModule
    )
}