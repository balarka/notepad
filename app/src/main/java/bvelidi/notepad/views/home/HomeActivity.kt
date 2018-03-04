package bvelidi.notepad.views.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import bvelidi.notepad.NotepadApp
import bvelidi.notepad.R
import bvelidi.notepad.model.Notes
import bvelidi.notepad.model.notes.NotesRepository
import bvelidi.notepad.views.notes.NotesDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity(), NotesListFragment.OnListFragmentInteractionListener {

    val kResultCodeSaveNotes = 1

    override fun onListFragmentInteraction(item: Notes) {
        val intent = Intent(this, NotesDetailActivity::class.java).apply {
            putExtra("text", item.content)
            putExtra("id", item.id)
        }
        startActivityForResult(intent, kResultCodeSaveNotes)
    }


    val zero_state = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        initView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun initView() {
        if (zero_state) {
            findViewById<View>(R.id.listFragment).visibility = View.GONE
        } else {
            findViewById<View>(R.id.zeroStateFragment).visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            kResultCodeSaveNotes -> {
                val id = data?.getIntExtra("id", -1)
                val content = data?.getStringExtra("content")
                val title = data?.getStringExtra("title")


                val tk = NotepadApp.database?.notesDao()?.getNotesForId(id!!)
                val updateNotes = Notes(id!!, title!!, content!!, tk?.date_created!!, Date().time.toString())

                NotesRepository.updateNotes(updateNotes)
                System.out.println("updated db note")
            }
        }
    }
}
