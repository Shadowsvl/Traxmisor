package com.arch.data.repository

import com.arch.common.result.Result
import com.traxion.model.data.User
import com.traxion.model.data.UserLocation

interface UserRepository {

    suspend fun getUser(userId: String): Result<User>

    suspend fun sendUserLocation(userLocation: UserLocation): Result<Any>
}