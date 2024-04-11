package com.arch.data.domain

import com.arch.common.result.Result
import com.arch.network.data_source.AuthNetworkDataSource
import com.traxion.model.data.AuthValidation
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authNetworkDataSource: AuthNetworkDataSource
) {

    suspend operator fun invoke(email: String, password: String): Result<AuthValidation> =
        authNetworkDataSource.logIn(email, password)
}