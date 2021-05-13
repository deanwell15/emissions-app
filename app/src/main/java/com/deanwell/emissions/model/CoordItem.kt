package com.deanwell.testlist.model


import com.google.gson.annotations.SerializedName

data class CoordItem(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)