package com.example.timetonicapp.data.network

import com.example.timetonicapp.data.model.UserAllBooks
import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.model.UserSessionKeyResult
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TimeTonicClient {
    @POST("live/api.php")
    suspend fun userLogin(
        @Query("version") version: String,
        @Query("req") req: String,
        @Query("login") login: String,
        @Query("pwd") pwd: String,
        @Query("appkey") appKey: String,
    ): Response<UserOAuthResult>

    @POST("live/api.php")
    suspend fun userSessionKey(
        @Query("version") version: String,
        @Query("req") req: String,
        @Query("o_u") oU: String,
        @Query("u_c") uC: String,
        @Query("oauthkey") oauthKey: String,
    ): Response<UserSessionKeyResult>

    @POST("live/api.php")
    suspend fun getAllBooks(
        @Query("version") version: String,
        @Query("req") req: String,
        @Query("u_c") uC: String,
        @Query("o_u") oU: String,
        @Query("sesskey") sessKey: String,
    ): Response<UserAllBooks>
}