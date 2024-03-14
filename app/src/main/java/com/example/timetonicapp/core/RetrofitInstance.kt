package com.example.timetonicapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun makeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://timetonic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}