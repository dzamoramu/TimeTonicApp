package com.example.timetonicapp.domain

import com.example.timetonicapp.data.model.UserAllBooks
import com.example.timetonicapp.data.repositories.UserBooksRepository

class UserBooksUseCase {

    private val repository = UserBooksRepository()

    suspend operator fun invoke(oU: String, uC: String, sessionKey: String): UserAllBooks? {
        return repository.getAllBooks(
            oU = oU,
            uC = uC,
            sessionKey = sessionKey
        )
    }
}