package bvelidi.notepad.views.home

import android.content.Intent
import android.os.Bundle
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

    private val kResultCodeSaveNotes = 1
    private val zeroState = false

    override fun onListFragmentInteraction(item: Notes) {
        val intent = Intent(this, NotesDetailActivity::class.java).apply {
            putExtra("titleText", item.title)
            putExtra("contentText", item.content)
            putExtra("id", item.id)
        }
        startActivityForResult(intent, kResultCodeSaveNotes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        initView()

        fab.setOnClickListener { view ->
            val intent = Intent(this, NotesDetailActivity::class.java)
            startActivityForResult(intent, kResultCodeSaveNotes)
        }
    }

    fun initView() {
        if (zeroState) {
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
                val updated = data?.getBooleanExtra("updated", false) ?: false
                if (updated) {
                    val fragment = supportFragmentManager.findFragmentById(R.id.listFragment) as NotesListFragment
                    fragment.refreshView()
                }
            }
        }
    }
}
