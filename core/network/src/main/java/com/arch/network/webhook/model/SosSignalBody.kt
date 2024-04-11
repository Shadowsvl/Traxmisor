package com.arch.network.webhook.model

import com.google.gson.annotations.SerializedName

data class SosSignalBody(
    @SerializedName("uid") val userId: String,
    @SerializedName("signalMessage") val signalMessage: String,
    @SerializedName("timestamp") val timestamp: Long
)
