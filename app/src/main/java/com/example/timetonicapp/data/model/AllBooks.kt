package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class AllBooks(
    @SerializedName("books") val books: List<Book>,
    @SerializedName("contacts") val contacts: List<Contact>,
    @SerializedName("nbBooks") val nbBooks: Int,
    @SerializedName("nbContacts") val nbContacts: Int,
    @SerializedName("req") val req: String,
)