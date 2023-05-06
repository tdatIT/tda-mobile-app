package com.tda.app.data.repository

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import javax.inject.Inject

private const val PREFERENCES_NAME = "USER_LOGGED"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreUserLogged @Inject constructor(private val context: Context) {
    private val JWT_KEY = stringPreferencesKey("JWT_KEY")
    private val EXPIRY_DATE = stringPreferencesKey("EXPIRY_DATE")

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val getJwt: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[JWT_KEY] ?: ""
        }

    suspend fun saveJwt(jwt: String) {
        context.dataStore.edit { preferences ->
            preferences[JWT_KEY] = jwt
        }
    }

    suspend fun removeJwt() {
        context.dataStore.edit { t -> t.remove(JWT_KEY) }
    }

    val getExpiryDate: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[EXPIRY_DATE] ?: ""
    }

    suspend fun saveExpiryDate(date: Date) {
        val expiry = dateFormat.format(date)
        context.dataStore
            .edit { preferences -> preferences[EXPIRY_DATE] = expiry }
    }


}