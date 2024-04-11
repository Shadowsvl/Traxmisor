package com.arch.network.data_source

import android.content.Context
import com.arch.common.network.AppDispatchers
import com.arch.common.network.Dispatcher
import com.arch.common.result.Result
import com.arch.network.ApiResponse
import com.arch.network.BuildConfig
import com.arch.network.RetrofitApiFactory
import com.arch.network.asApiResponse
import com.arch.network.util.ClientConfig
import com.arch.network.webhook.WebhookApi
import com.arch.network.webhook.mapper.asUserLocationBody
import com.google.gson.Gson
import com.traxion.model.data.UserLocation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebhookNetwork @Inject constructor(
    @ApplicationContext context: Context,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    gson: Gson
) : WebhookNetworkDataSource {

    private val clientConfig = ClientConfig.Builder(baseUrl = BuildConfig.API_BASE_URL)
        .cacheEnabled(true)
        .build()

    private val client = RetrofitApiFactory(gson).createClient(context, clientConfig)

    private val api = client.getApiService(WebhookApi::class.java)

    override suspend fun sendUserLocation(userLocation: UserLocation): Result<Any> {
        return api.postUserLocation(userLocation.asUserLocationBody()).toResult { it.string() }
    }

    private suspend fun <T, R> Response<T>.toResult(dataProcess: (T) -> R): Result<R> = withContext(ioDispatcher) {
        when(val response = asApiResponse()) {
            is ApiResponse.Success -> response.data?.let { Result.Success(dataProcess(it)) }
                ?: Result.Error(IOException("Data not retrieved"))
            is ApiResponse.HttpError -> Result.Error(IOException(response.statusMessage))
            is ApiResponse.Failure -> Result.Error(response.exception)
        }
    }
}