package com.arch.network.data_source

import com.arch.common.result.Result
import com.traxion.model.data.AuthValidation

interface AuthNetworkDataSource {

    suspend fun logIn(email: String, password: String): Result<AuthValidation>
}