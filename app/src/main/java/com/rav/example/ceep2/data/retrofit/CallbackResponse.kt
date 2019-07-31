package com.rav.example.ceep2.data.retrofit

interface CallbackResponse<T> {
    fun success(response: T)
}