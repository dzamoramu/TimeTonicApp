package com.example.timetonicapp.domain

import com.example.timetonicapp.data.model.UserSessionKeyResult
import com.example.timetonicapp.data.repositories.LoginRepository

class UserSessionKeyUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(oU: String, uC: String, oauthKey: String): UserSessionKeyResult? {
        return repository.getSessionKey(
            oU = oU,
            uC = uC,
            oauthKey = oauthKey
        )
    }
}