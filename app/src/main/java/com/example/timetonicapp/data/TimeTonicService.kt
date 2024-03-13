package com.example.timetonicapp.data

import com.example.timetonicapp.BuildConfig
import com.example.timetonicapp.core.RetrofitInstance
import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.network.TimeTonicClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimeTonicService {

    private val retrofit = RetrofitInstance.makeRetrofit()

    suspend fun doLogin(user: String, password: String): UserOAuthResult? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TimeTonicClient::class.java).userLogin(
                version = "6.41",
                login = user,
                pwd = password,
                BuildConfig.APP_KEY
            )
            response.body()
        }
    }
}