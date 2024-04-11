package com.arch.network.webhook.model

import com.google.gson.annotations.SerializedName

data class UserLocationBody(
    @SerializedName("uid") val userId: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double,
    @SerializedName("accuracy") val accuracy: Float,
    @SerializedName("timestamp") val timestamp: Long
)
