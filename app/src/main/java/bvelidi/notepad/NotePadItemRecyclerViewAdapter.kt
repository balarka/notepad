package bvelidi.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bvelidi.notepad.model.Notes
import bvelidi.notepad.views.home.NotesListFragment.OnListFragmentInteractionListener

/**
 * [RecyclerView.Adapter] that can display a Notes and makes a call to the
 * specified [OnListFragmentInteractionListener].
 *
 */
class NotePadItemRecyclerViewAdapter(private var mValues: List<Notes>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<NotePadItemRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notepaditem, parent, false)
        return ViewHolder(view)
    }

    fun setList(listItems: List<Notes>) {
        mValues = listItems
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem as Notes)
        }
    }


    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView
        var mItem: Notes? = null

        init {
            mContentView = mView.findViewById<View>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
