package com.example.timetonicapp.data.network

import com.example.timetonicapp.data.model.UserOAuthResult
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TimeTonicClient {
    @POST
    suspend fun userLogin(
        @Query("version") version: String,
        @Query("login") login: String,
        @Query("pwd") pwd: String,
        @Query("appkey") appKey: String,
    ): Response<UserOAuthResult>
}