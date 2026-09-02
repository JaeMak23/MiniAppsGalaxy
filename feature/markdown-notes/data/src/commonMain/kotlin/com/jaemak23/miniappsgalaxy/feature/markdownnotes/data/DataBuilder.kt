package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data

import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteDatabase

expect fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase>