package com.example.timetonicapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun makeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://timetonic.com/live/api.php")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}