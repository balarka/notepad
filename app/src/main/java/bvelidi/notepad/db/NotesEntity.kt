package bvelidi.notepad.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by bvelidi on 3/2/18.
 */

@Entity(tableName = "Notes")
data class NotesEntity(@ColumnInfo(name = "title") val title: String,
                       @ColumnInfo(name = "content") val content: String,
                       @ColumnInfo(name = "date_created") val date_modified: String,
                       @ColumnInfo(name = "date_modified") val date_created: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}