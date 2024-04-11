package com.arch.network.webhook.mapper

import com.arch.network.webhook.model.UserLocationBody
import com.traxion.model.data.UserLocation

fun UserLocation.asUserLocationBody() = UserLocationBody(
    userId = userId,
    latitude = latitude,
    longitude = longitude,
    accuracy = accuracy,
    timestamp = timestamp
)