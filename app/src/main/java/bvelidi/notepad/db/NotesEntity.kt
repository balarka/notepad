package bvelidi.notepad.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by bvelidi on 3/2/18.
 */

@Entity(tableName = "Notes")
class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    @ColumnInfo(name = "date_created")
    var date_created: String? = null

    @ColumnInfo(name = "date_modified")
    var date_modified: String? = null
}