package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class UserAllBooks(
    @SerializedName("allBooks") val allBooks: AllBooks,
    @SerializedName("status") val status: String,
)