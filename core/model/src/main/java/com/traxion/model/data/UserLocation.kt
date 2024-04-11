package com.traxion.model.data

data class UserLocation(
    val userId: String,
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float,
    val timestamp: Double
)
