package com.rav.example.ceep2.data.service

import com.rav.example.ceep2.data.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NoteService {

    @GET("/notes")
    fun list(): Call<List<Note>>

    @POST("/notes")
    fun insert(@Body note: Note): Call<Note>
}