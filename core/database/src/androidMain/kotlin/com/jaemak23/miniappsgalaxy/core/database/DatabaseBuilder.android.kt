package com.jaemak23.miniappsgalaxy.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.core.database.local.NoteDatabase
import org.koin.core.context.GlobalContext

actual fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase> {
    val context: Context = GlobalContext.get().get()
    val dbFile = context.getDatabasePath("markdown_notes.db")
    return Room.databaseBuilder<NoteDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}