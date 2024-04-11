package com.arch.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/*
* Class to handle the app preferences via DataStore library
* */
@Singleton
class AppDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val IS_lOGGED = booleanPreferencesKey("is_logged")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    private val store = context.dataStore

    val isLogged: Flow<Boolean> = store.data.map { it[IS_lOGGED] ?: false }

    suspend fun setIsLogged(value: Boolean) {
        store.edit { it[IS_lOGGED] = value }
    }

    val userId: Flow<String> = store.data.map { it[USER_ID] ?: "" }

    suspend fun setUserId(value: String) {
        store.edit { it[USER_ID] = value }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")