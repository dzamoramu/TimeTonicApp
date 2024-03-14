package com.example.timetonicapp.data.repositories

import com.example.timetonicapp.data.TimeTonicService
import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.model.UserSessionKeyResult

class LoginRepository {

    private val api = TimeTonicService()

    suspend fun doLogin(user: String, password: String): UserOAuthResult? {
        return api.doLogin(
            user = user,
            password = password
        )
    }

    suspend fun getSessionKey(oU: String, uC: String, oauthKey: String): UserSessionKeyResult? {
        return api.getSessionKey(
            oU = oU,
            uC = uC,
            oauthKey = oauthKey
        )
    }
}