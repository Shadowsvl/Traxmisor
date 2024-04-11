package com.arch.network.data_source

import com.arch.common.result.Result
import com.traxion.model.data.AuthValidation
import com.traxion.model.data.User
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/*
* Mock implementation of the auth functions
* */
@Singleton
class MockAuthNetwork @Inject constructor() : AuthNetworkDataSource {

    override suspend fun logIn(email: String, password: String): Result<AuthValidation> {
        delay(600)
        return Result.Success(AuthValidation(isLogged = true, userId = "1"))
    }

    override suspend fun getUser(userId: String): Result<User> {
        delay(400)
        return Result.Success(
            User(
                id = "1",
                fullName = "John Hopkins",
                vehicle = "Trailer",
                plates = "WLU9769",
                profileImage = "https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"
            )
        )
    }
}