package com.jaemak23.miniappsgalaxy.core.database

import androidx.room.RoomDatabase
import com.jaemak23.miniappsgalaxy.core.database.local.NoteDatabase

expect fun getNoteDatabaseBuilder(): RoomDatabase.Builder<NoteDatabase>