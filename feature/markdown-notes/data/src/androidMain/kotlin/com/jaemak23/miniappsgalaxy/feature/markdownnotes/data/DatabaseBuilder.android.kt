package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteDatabase
import org.koin.core.context.GlobalContext
import android.content.Context

actual fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase> {
    val context: Context = GlobalContext.get().get()
    val dbFile = context.getDatabasePath("markdown_notes.db")
    return Room.databaseBuilder(context, NoteDatabase::class.java, dbFile.absolutePath)
}