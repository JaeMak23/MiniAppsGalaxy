package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteDatabase
import java.io.File

actual fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase> {
    val dbFile = File(System.getProperty("user.home"), ".miniappsgalaxy/markdown_notes.db")
    dbFile.parentFile?.mkdirs()
    return Room.databaseBuilder(dbFile.absolutePath)
}