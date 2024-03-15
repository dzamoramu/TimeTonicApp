package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class OwnerPrefs(
    @SerializedName("oCoverImg") val oCoverImg: String?,
    @SerializedName("title") val title: String?
)