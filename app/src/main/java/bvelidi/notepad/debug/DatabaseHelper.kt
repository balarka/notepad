package bvelidi.notepad.debug

import bvelidi.notepad.NotepadApp
import bvelidi.notepad.db.NotesEntity
import java.util.*

object DatabaseHelper {

    fun initDB() {
        for (i in 1..5) {
            val dateNow = Date().time.toString()
            val n = NotesEntity(i.toString(), i.toString(), dateNow, dateNow)
            NotepadApp.database.notesDao().insert(n)
        }
    }
}