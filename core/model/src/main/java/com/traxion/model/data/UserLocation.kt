package com.traxion.model.data

data class UserLocation(
    val userId: String,
    val latitude: Float,
    val longitude: Float,
    val accuracy: Float,
    val timestamp: Double
)
