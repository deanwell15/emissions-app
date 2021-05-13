package com.deanwell.emissions.model


import com.google.gson.annotations.SerializedName

data class More(
    @SerializedName("airplane")
    val airplane: String,
    @SerializedName("coefficientoftheseatsless")
    val coefficientoftheseatsless: String,
    @SerializedName("coefficientoftheseatsmax")
    val coefficientoftheseatsmax: String,
    @SerializedName("data")
    val `data`: String,
    @SerializedName("emissionsforpointless")
    val emissionsforpointless: Double,
    @SerializedName("emissionsforpointmax")
    val emissionsforpointmax: Double,
    @SerializedName("km")
    val km: Double,
    @SerializedName("totalemissionsless")
    val totalemissionsless: Double,
    @SerializedName("totalemissionsmax")
    val totalemissionsmax: Double,
    @SerializedName("totalfuelwastedless")
    val totalfuelwastedless: Double,
    @SerializedName("totalfuelwastedmax")
    val totalfuelwastedmax: Double
)