package com.arch.network.webhook

import com.arch.network.webhook.model.UserLocationBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebhookApi {

    @POST("e510bf9b39d491442ffe8993446bab95")
    suspend fun postUserLocation(
        @Body body: UserLocationBody
    ): Response<ResponseBody>

}