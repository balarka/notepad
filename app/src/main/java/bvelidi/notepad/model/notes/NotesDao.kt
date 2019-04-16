package bvelidi.notepad.model.notes

import androidx.room.*
import bvelidi.notepad.db.NotesEntity

/**
 * Created by bvelidi on 2/15/18.
 */

@Dao
interface NotesDao {
    @Query("SELECT * from Notes")
    fun getAll(): List<NotesEntity>

    @Query("SELECT * from Notes where id = :id")
    fun getNotesForId(id: Int): NotesEntity

    @Insert
    fun insertAll(vararg notes: NotesEntity)

    @Insert
    fun insert(notes: NotesEntity)

    @Delete
    fun delete(notes: NotesEntity)

    @Query("UPDATE Notes SET title= :title, content= :content, date_created= :date_created, date_modified= :date_modified WHERE id= :id")
    fun update(id: Int, title: String, content: String, date_created: String, date_modified: String)
}