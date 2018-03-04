package bvelidi.notepad.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import bvelidi.notepad.model.notes.NotesDao

/**
 * Created by bvelidi on 3/2/18.
 */

@Database(entities = arrayOf(NotesEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}