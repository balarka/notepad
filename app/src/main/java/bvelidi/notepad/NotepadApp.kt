package bvelidi.notepad

import android.app.Application
import androidx.room.Room
import bvelidi.notepad.db.AppDatabase
import bvelidi.notepad.debug.DatabaseHelper

/**
 * Created by bvelidi on 3/2/18.
 */

class NotepadApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "notes-db")
                .allowMainThreadQueries().build()

        // Initialize DB with some data for Debug builds.
        if (!BuildConfig.DEBUG) {
            DatabaseHelper.initDB()
        }
    }
}