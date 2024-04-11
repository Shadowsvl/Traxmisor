package com.arch.network.webhook.model

import com.google.gson.annotations.SerializedName

data class UserLocationBody(
    @SerializedName("uid") val userId: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lng") val longitude: Float,
    @SerializedName("accuracy") val accuracy: Float,
    @SerializedName("timestamp") val timestamp: Double
)
