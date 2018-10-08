package bvelidi.notepad

import android.app.Application
import android.arch.persistence.room.Room
import bvelidi.notepad.db.AppDatabase
import bvelidi.notepad.debug.DatabaseHelper

/**
 * Created by bvelidi on 3/2/18.
 */

class NotepadApp: Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        if(NotepadApp.database == null) {
            NotepadApp.database = Room.databaseBuilder(this, AppDatabase::class.java, "notes-db")
                    .allowMainThreadQueries().build()

            // Initialize DB with some data for Debug builds.
            if(BuildConfig.DEBUG) {
                DatabaseHelper.initDB()
            }
        }
    }
}