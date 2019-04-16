package bvelidi.notepad.views.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import bvelidi.notepad.NotepadApp
import bvelidi.notepad.R
import bvelidi.notepad.db.NotesEntity
import kotlinx.android.synthetic.main.activity_notes_detail.*
import java.util.*


class NotesDetailActivity : AppCompatActivity() {
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val title = if (intent.hasExtra("titleText")) {
            intent.getStringExtra("titleText")
        } else {
            resources.getString(R.string.new_note_title)
        }

        titleText.setText(title)

        id = intent.getIntExtra("id", -1)
        val content = if (intent.hasExtra("contentText")) {
            intent.getStringExtra("contentText")
        } else {
            resources.getString(R.string.empty_note_zero_text)
        }

        contentText.setText(content)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                if (id == -1) {
                    val date_created_modified = Date().time.toString()
                    NotepadApp.database.notesDao().insert(NotesEntity(titleText.text.toString(), contentText.text.toString(), date_created_modified, date_created_modified))
                } else {
                    val date_created = NotepadApp.database.notesDao().getNotesForId(id).date_created
                    NotepadApp.database.notesDao().update(id, titleText.text.toString(), contentText.text.toString(), date_created, Date().time.toString())
                }
                val intent = Intent()
                        .putExtra("updated", true)
                setResult(1, intent)
                finish()
            }
        }
        return true
    }
}
