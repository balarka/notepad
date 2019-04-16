package bvelidi.notepad.db

import androidx.room.Database
import androidx.room.RoomDatabase
import bvelidi.notepad.model.notes.NotesDao

/**
 * Created by bvelidi on 3/2/18.
 */

@Database(entities = arrayOf(NotesEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}