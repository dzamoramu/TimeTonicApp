package com.example.timetonicapp.domain

import com.example.timetonicapp.data.model.UserOAuthResult
import com.example.timetonicapp.data.repositories.LoginRepository

class LoginUseCase {

    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String) : UserOAuthResult? {
        return repository.doLogin(user = user, password = password)
    }
}