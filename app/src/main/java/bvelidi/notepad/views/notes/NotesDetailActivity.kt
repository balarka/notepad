package bvelidi.notepad.views.notes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import bvelidi.notepad.R
import kotlinx.android.synthetic.main.activity_notes_detail.*


class NotesDetailActivity : AppCompatActivity() {
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_detail)
        setTitle(R.string.edit_notes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val text = intent.getStringExtra("text")
        id = intent.getIntExtra("id", -1)

        editText.setText(text, TextView.BufferType.EDITABLE)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                val intent = Intent().putExtra("text", editText.text.toString())
                        .putExtra("id", id)
                setResult(1, intent)
                finish()
            }
        }
        return true
    }
}
