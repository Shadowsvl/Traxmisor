package com.arch.data.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    fun isLogged(): Flow<Boolean>

    suspend fun setIsLogged(value: Boolean)

    fun userId(): Flow<String>

    suspend fun setUserId(value: String)
}