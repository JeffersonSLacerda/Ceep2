package com.rav.example.ceep2.ui.note

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rav.example.ceep2.R
import com.rav.example.ceep2.data.model.Note
import com.rav.example.ceep2.data.retrofit.CallbackResponse
import com.rav.example.ceep2.data.web.NoteWebClient
import com.rav.example.ceep2.ui.note.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.form_note.view.*

class NoteListActivity : AppCompatActivity() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list(object: CallbackResponse<List<Note>>{
            override fun success(notes: List<Note>) {
                this@NoteListActivity.notes.addAll(notes)
                configureList()
            }
        })



        fab_add_note.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val createdView = LayoutInflater.from(this@NoteListActivity)
                    .inflate(R.layout.form_note,
                    window.decorView as ViewGroup,
                    false)

                AlertDialog.Builder(this@NoteListActivity)
                    .setTitle("Add note")
                    .setView(createdView)
                    .setPositiveButton("Save", object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val title = createdView.form_note_title.text.toString()
                            val description = createdView.form_note_description.text.toString()
                            val note = Note(title, description)
                            NoteWebClient().insert(note, object: CallbackResponse<Note>{
                                override fun success(response: Note) {
                                    this@NoteListActivity.notes.add(note)
                                    configureList()
                                }
                            })
                        }
                    })
                    .show()
            }

        })
    }

    private fun configureList(){

        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layouManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layouManager

    }

}
