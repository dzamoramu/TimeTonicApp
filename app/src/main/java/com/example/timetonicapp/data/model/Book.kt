package com.example.timetonicapp.data.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("b_c") val bC: String,
    @SerializedName("b_0") val bO: String,
    @SerializedName("contact_u_c") val contactUC : Any,
    @SerializedName("favorite") val favorite: Boolean,
    @SerializedName("members") val members: List<Member>,
    @SerializedName("order") val order: Int,
    @SerializedName("ownerPrefs") val ownerPrefs: OwnerPrefs,
)