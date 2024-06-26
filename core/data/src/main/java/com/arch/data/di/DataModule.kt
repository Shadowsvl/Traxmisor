package com.arch.data.di

import com.arch.data.location.FusedLocationMonitor
import com.arch.data.location.LocationMonitor
import com.arch.data.repository.DataStoreRepository
import com.arch.data.repository.DefaultUserRepository
import com.arch.data.repository.PreferenceRepository
import com.arch.data.repository.UserRepository
import com.arch.data.util.ConnectivityManagerNetworkMonitor
import com.arch.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

    @Binds
    fun bindsLocationMonitor(
        fusedLocationMonitor: FusedLocationMonitor
    ): LocationMonitor

    @Binds
    fun bindsPreferenceRepository(
        dataStoreRepository: DataStoreRepository
    ): PreferenceRepository

    @Binds
    fun bindsUserRepository(
        defaultUserRepository: DefaultUserRepository
    ): UserRepository
}