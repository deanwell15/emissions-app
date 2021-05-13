package com.deanwell.testlist.model


import com.google.gson.annotations.SerializedName

data class ReqresItem(
    @SerializedName("airplane")
    val airplane: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("flight")
    val flight: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)