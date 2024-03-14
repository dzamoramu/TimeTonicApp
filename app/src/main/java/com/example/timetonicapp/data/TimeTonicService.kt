package com.example.timetonicapp.data

import com.example.timetonicapp.BuildConfig
import com.example.timetonicapp.core.RetrofitInstance
import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.model.UserSessionKeyResult
import com.example.timetonicapp.data.network.TimeTonicClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimeTonicService {

    private val retrofit = RetrofitInstance.makeRetrofit()

    suspend fun doLogin(user: String, password: String): UserOAuthResult? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TimeTonicClient::class.java).userLogin(
                version = VERSION,
                req = "createOauthkey",
                login = user,
                pwd = password,
                appKey = BuildConfig.APP_KEY
            )
            response.body()
        }
    }

    suspend fun getSessionKey(oU: String, uC: String, oauthKey: String): UserSessionKeyResult? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TimeTonicClient::class.java).userSessionKey(
                version = VERSION,
                req = "createSesskey",
                oU = oU,
                uC = uC,
                oauthKey = oauthKey
            )
            response.body()
        }
    }

    companion object {
        const val VERSION = "6.41"
    }
}