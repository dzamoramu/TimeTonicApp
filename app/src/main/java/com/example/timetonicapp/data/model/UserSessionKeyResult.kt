package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class UserSessionKeyResult(
    @SerializedName("createdVNB") val createdVNB: String,
    @SerializedName("id") val id: String,
    @SerializedName("req") val req: String,
    @SerializedName("sesskey") val sessionKey: String,
    @SerializedName("status") var status: String
)