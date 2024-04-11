package com.arch.data.repository

import com.arch.data_store.AppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val appDataStore: AppDataStore
) : PreferenceRepository {

    override fun isLogged(): Flow<Boolean> = appDataStore.isLogged

    override suspend fun setIsLogged(value: Boolean) = appDataStore.setIsLogged(value)
}