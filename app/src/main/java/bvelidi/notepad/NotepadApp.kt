package bvelidi.notepad

import android.app.Application
import android.arch.persistence.room.Room
import bvelidi.notepad.db.AppDatabase
import bvelidi.notepad.db.NotesEntity
import java.util.*

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
            // TODO: Initialize Db only for testing purposes, remove it in prod
            initDB()
        }
    }

    private fun initDB() {
        for (i in 1..50) {
            val n = NotesEntity()
            val dateNow = Date().time.toString()
            n.title = i.toString()
            n.content = i.toString()
            n.date_created = dateNow
            n.date_modified = n.date_created
            NotepadApp.database?.notesDao()?.insert(n)
        }
    }
}