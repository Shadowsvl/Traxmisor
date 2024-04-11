package com.arch.network.data_source

import com.arch.common.result.Result
import com.traxion.model.data.AuthValidation
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockAuthNetwork @Inject constructor() : AuthNetworkDataSource {

    override suspend fun logIn(email: String, password: String): Result<AuthValidation> {
        delay(600)
        return Result.Success(AuthValidation(isLogged = true, userId = "1"))
    }
}