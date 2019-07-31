package com.rav.example.ceep2.data.retrofit

import com.rav.example.ceep2.data.service.NoteService
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    private val httpClient = OkHttpClient.Builder()

    private val client =
        httpClient
            .readTimeout(220, TimeUnit.SECONDS)
            .connectTimeout(220, TimeUnit.SECONDS)
            .build()

    private val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("http://192.168.1.59:8080")
            .addConverterFactory(GsonConverterFactory.create()).client(client)
            .build()

    fun noteService() = retrofit.create(NoteService::class.java)

}