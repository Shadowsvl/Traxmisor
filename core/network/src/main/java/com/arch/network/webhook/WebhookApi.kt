package com.arch.network.webhook

import com.arch.network.webhook.model.SosSignalBody
import com.arch.network.webhook.model.UserLocationBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebhookApi {

    @POST("57e4f6a329337cf65c2a9e69429f05e1")
    suspend fun postUserLocation(
        @Body body: UserLocationBody
    ): Response<ResponseBody>

    @POST("76a3081ce62558c04dd623b586bf6113")
    suspend fun postSosSignal(
        @Body body: SosSignalBody
    ): Response<ResponseBody>

}