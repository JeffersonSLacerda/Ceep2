package com.rav.example.ceep2.data.retrofit

import com.rav.example.ceep2.data.model.Note

interface CallbackResponse<T> {
    fun success(response: T)
}