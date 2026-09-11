package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data

import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data.local.NoteDatabase

expect fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase>