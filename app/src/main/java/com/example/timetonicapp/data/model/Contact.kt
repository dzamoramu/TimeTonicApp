package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("isConfirmed") val isConfirmed: Boolean,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("sstamp") val sstamp: Int,
    @SerializedName("u_c") val uC: String,
)