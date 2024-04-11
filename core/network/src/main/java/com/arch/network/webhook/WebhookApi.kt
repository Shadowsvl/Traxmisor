package com.arch.network.webhook

import com.arch.network.webhook.model.UserLocationBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebhookApi {

    @POST("634d56766487f88222e719f79a9e43bc")
    suspend fun postUserLocation(
        @Body body: UserLocationBody
    ): Response<ResponseBody>

}