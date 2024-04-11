package com.arch.network.data_source

import com.arch.common.result.Result
import com.traxion.model.data.AuthValidation
import com.traxion.model.data.User

interface AuthNetworkDataSource {

    suspend fun logIn(email: String, password: String): Result<AuthValidation>

    suspend fun getUser(userId: String): Result<User>
}