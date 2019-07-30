package com.rav.example.ceep2.data.retrofit

import com.rav.example.ceep2.data.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.59:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun noteService() = retrofit.create(NoteService::class.java)

}