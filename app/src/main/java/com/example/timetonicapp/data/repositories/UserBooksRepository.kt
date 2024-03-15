package com.example.timetonicapp.data.repositories

import com.example.timetonicapp.data.TimeTonicService
import com.example.timetonicapp.data.model.UserAllBooks

class UserBooksRepository {

    private val api = TimeTonicService()

    suspend fun getAllBooks(oU: String, uC: String, sessionKey: String) : UserAllBooks? {
        return api.getAllBooks(
            oU = oU,
            uC = uC,
            sessionKey = sessionKey
        )
    }
}