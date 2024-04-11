package com.arch.network.data_source

import com.arch.common.result.Result
import com.traxion.model.data.UserLocation

interface WebhookNetworkDataSource {

    suspend fun sendUserLocation(userLocation: UserLocation): Result<Any>

    suspend fun sendSosSignal(userId: String, signalMessage: String): Result<Any>
}