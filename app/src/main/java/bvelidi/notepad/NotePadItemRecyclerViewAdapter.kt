package bvelidi.notepad

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bvelidi.notepad.model.Notes
import bvelidi.notepad.views.NotesListFragment.OnListFragmentInteractionListener

/**
 * [RecyclerView.Adapter] that can display a Notes and makes a call to the
 * specified [OnListFragmentInteractionListener].
 *
 */
class NotePadItemRecyclerViewAdapter(private val mValues: List<Notes>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<NotePadItemRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_notepaditem, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].id.toString()
        holder.mContentView.text = mValues[position].text

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem as Notes)
        }
    }


    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView
        var mItem: Notes? = null


        init {
            mIdView = mView.findViewById<View>(R.id.id) as TextView
            mContentView = mView.findViewById<View>(R.id.content) as TextView
        }


        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
