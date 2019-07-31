package com.rav.example.ceep2.ui.note.adapter

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rav.example.ceep2.R
import com.rav.example.ceep2.data.model.Note
import com.rav.example.ceep2.ui.note.NoteListActivity

class NoteListAdapter(
    private var notes: List<Note>,
    private var windowManager: WindowManager,
    noteListActivity: NoteListActivity
): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTitle.text = notes[position].title
        holder.noteDescription.text = notes[position].description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTitle: TextView = itemView.findViewById(R.id.note_item_title)
        val noteDescription: TextView = itemView.findViewById(R.id.note_item_description)

    }

}
