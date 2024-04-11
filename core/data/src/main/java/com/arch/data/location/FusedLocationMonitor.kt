package com.arch.data.location

import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.traxion.model.data.LocationSample
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FusedLocationMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationMonitor {

    companion object {
        private const val TAG = "LOCATION_MONITOR"
    }

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    override val locationUpdates: Flow<LocationSample> = callbackFlow {

        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 12000).setWaitForAccurateLocation(false).build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)

                result.locations.lastOrNull()?.let {location: Location ->
                    launch {
                        val sample = LocationSample(latitude = location.latitude, longitude = location.longitude, accuracy = location.accuracy)
                        Log.d(TAG, "lat: ${sample.latitude}, lng: ${sample.longitude}")
                        channel.trySend(sample)
                    }
                }

            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
            Log.d(TAG, "Monitor Started")
        } catch (e: SecurityException) {
            Log.d(TAG, "Exception occurred", e)
            channel.close(cause = e)
        }

        awaitClose {
            Log.d(TAG, "Monitor Closed")
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}