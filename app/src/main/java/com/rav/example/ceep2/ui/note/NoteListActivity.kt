package com.rav.example.ceep2.ui.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rav.example.ceep2.R
import com.rav.example.ceep2.data.model.Note
import com.rav.example.ceep2.data.retrofit.RetrofitInitializer
import com.rav.example.ceep2.ui.note.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.activity_note_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val cal = RetrofitInitializer().noteService().list()
        cal.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(
                call: Call<List<Note>?>?,
                response: Response<List<Note>?>?
            ) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    configureList(notes)
                }
            }

            override fun onFailure(
                call: Call<List<Note>?>?,
                t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })

    }
        private fun configureList(notes: List<Note>){

            val recyclerView = note_list_recyclerview
            recyclerView.adapter = NoteListAdapter(notes, this)
            val layouManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.layoutManager = layouManager

        }

}
