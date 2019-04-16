package bvelidi.notepad.views.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bvelidi.notepad.NotePadItemRecyclerViewAdapter
import bvelidi.notepad.NotepadApp
import bvelidi.notepad.R
import bvelidi.notepad.model.Notes
import bvelidi.notepad.model.notes.NotesRepository
import bvelidi.notepad.views.home.NotesListFragment.OnListFragmentInteractionListener

/**
 * A fragment representing a list of Items.
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */

class NotesListFragment : androidx.fragment.app.Fragment() {
    private var mListener: OnListFragmentInteractionListener? = null
    private lateinit var adapter: NotePadItemRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notepaditem_list, container, false)
        val allNotes = arrayListOf<Notes>()

        NotepadApp.database.notesDao().getAll().forEach { ne ->
            with(ne) {
                allNotes.add(Notes(id, title, content + " item", date_created, date_modified))
            }
        }

        adapter = NotePadItemRecyclerViewAdapter(allNotes, mListener)
        (view as androidx.recyclerview.widget.RecyclerView).adapter = adapter
        view.setHasFixedSize(true)
        return view
    }

    fun refreshView() {
        loadNotes()
        adapter.notifyDataSetChanged()
    }

    private fun loadNotes() {
        val newList = NotesRepository.getAllNotes()
        adapter.setList(newList)
    }

    override fun onAttach(context: Context) {
        context.let {
            super.onAttach(it)
            if (it is OnListFragmentInteractionListener) {
                mListener = it
            } else {
                throw RuntimeException("$it must implement OnListFragmentInteractionListener")
            }
        }
    }

    override fun onResume() {
        refreshView()
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Notes)
    }

    companion object {
        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): NotesListFragment {
            val fragment = NotesListFragment()
            return fragment
        }
    }
}
