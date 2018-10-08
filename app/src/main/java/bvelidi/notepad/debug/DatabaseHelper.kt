package bvelidi.notepad.debug

import bvelidi.notepad.NotepadApp
import bvelidi.notepad.db.NotesEntity
import java.util.*

object DatabaseHelper {

    fun initDB() {
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