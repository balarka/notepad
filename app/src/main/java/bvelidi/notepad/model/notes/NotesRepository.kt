package bvelidi.notepad.model.notes

import bvelidi.notepad.NotepadApp
import bvelidi.notepad.model.Notes

/**
 * Created by bvelidi on 3/4/18.
 */

object NotesRepository {

    fun getNotesForId(id: Int): Notes {
        val notesEntity = NotepadApp.database?.notesDao()?.getNotesForId(id!!)
        return Notes(notesEntity?.id!!, notesEntity.title!!, notesEntity.content!!, notesEntity.date_created!!, notesEntity.date_modified!!)
    }

    fun updateNotes(notes: Notes)= NotepadApp.database?.notesDao()?.update(notes.id, notes.title, notes.content, notes.dateCreated, notes.dateModified)

    fun getAllNotes(): List<Notes> = NotepadApp.database?.notesDao()?.getAll()!!.map { n -> Notes(n.id!!, n.title!!, n.content!!, n.date_created!!, n.date_modified!!) }
}