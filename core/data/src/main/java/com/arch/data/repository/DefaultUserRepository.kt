package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.network.data_source.AuthNetworkDataSource
import com.arch.network.data_source.WebhookNetworkDataSource
import com.traxion.model.data.User
import com.traxion.model.data.UserLocation
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val webhookNetworkDataSource: WebhookNetworkDataSource
) : UserRepository {

    override suspend fun getUser(userId: String): Result<User> = authNetworkDataSource.getUser(userId)

    override suspend fun sendUserLocation(userLocation: UserLocation): Result<Any> =
        webhookNetworkDataSource.sendUserLocation(userLocation)

    override suspend fun sendSosSignal(userId: String, signalMessage: String): Result<Any> =
        webhookNetworkDataSource.sendSosSignal(userId, signalMessage)
}