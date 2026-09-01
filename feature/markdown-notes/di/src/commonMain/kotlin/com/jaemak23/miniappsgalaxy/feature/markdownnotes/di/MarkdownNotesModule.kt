package com.jaemak23.miniappsgalaxy.feature.markdownnotes.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.RoomDraftDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.RoomNoteDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.getNoteDatabaseBuilder
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteDatabase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.DraftDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.NoteLocalDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.ClearDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.DeleteNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetNoteByIdUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetNotesUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.ImportNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.SaveDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.SaveNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list.NoteListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val markdownNotesDataModule = module {
    single {
        getNoteDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single<NoteDatabase> { get() }
    single { get<NoteDatabase>().noteDao }
    single { get<NoteDatabase>().draftDao }

    singleOf(::RoomNoteDataSource) bind NoteLocalDataSource::class
    singleOf(::RoomDraftDataSource) bind DraftDataSource::class
}

val markdownNotesDomainModule = module {
    factoryOf(::GetNotesUseCase)
    factoryOf(::GetNoteByIdUseCase)
    factoryOf(::SaveNoteUseCase)
    factoryOf(::DeleteNoteUseCase)
    factoryOf(::ImportNoteUseCase)
    factoryOf(::SaveDraftUseCase)
    factoryOf(::GetDraftUseCase)
    factoryOf(::ClearDraftUseCase)
    // ExportNoteUseCase intentionally omitted — depends on FileAccessDataSource, not yet scaffolded
}

val markdownNotesPresentationModule = module {
    viewModelOf(::NoteListViewModel)
}

val markdownNotesModule = module {
    includes(
        markdownNotesDataModule,
        markdownNotesDomainModule,
        markdownNotesPresentationModule
    )
}