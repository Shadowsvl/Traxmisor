package com.arch.network.di

import com.arch.network.data_source.AuthNetworkDataSource
import com.arch.network.data_source.MockAuthNetwork
import com.arch.network.data_source.WebhookNetwork
import com.arch.network.data_source.WebhookNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsAuthNetworkDS(
        mockAuthNetwork: MockAuthNetwork
    ): AuthNetworkDataSource

    @Binds
    fun bindsWebhookNetworkDS(
        webhookNetwork: WebhookNetwork
    ): WebhookNetworkDataSource
}