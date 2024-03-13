package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class UserOAuthResult(
    @SerializedName("createdVNB") val createdVNB: String,
    @SerializedName("id") val id: String,
    @SerializedName("o_u") val oU: String,
    @SerializedName("oauthkey") val oauthKey: String,
    @SerializedName("req") val req: String,
    @SerializedName("status") val status: String
)
