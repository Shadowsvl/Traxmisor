package com.traxion.model.mapper

import com.traxion.model.data.LocationSample
import com.traxion.model.data.UserLocation

fun LocationSample.asUserLocation(userId: String) = UserLocation(
    userId = userId,
    latitude = latitude,
    longitude = longitude,
    accuracy = accuracy,
    timestamp = timestamp
)