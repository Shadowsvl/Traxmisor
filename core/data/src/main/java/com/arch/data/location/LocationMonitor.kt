package com.arch.data.location

import com.traxion.model.data.LocationSample
import kotlinx.coroutines.flow.Flow

interface LocationMonitor {
    val locationUpdates: Flow<LocationSample>
}