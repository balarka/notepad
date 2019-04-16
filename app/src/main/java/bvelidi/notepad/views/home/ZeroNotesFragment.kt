package bvelidi.notepad.views.home

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bvelidi.notepad.R

/**
 * A placeholder fragment containing a simple view.
 */
class ZeroNotesFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_zero_notes, container, false)
    }
}
