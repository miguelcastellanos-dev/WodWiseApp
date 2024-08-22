package com.migueldev.wodwiseapp.data.session

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.migueldev.wodwiseapp.di.IO
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

val Context.dataStore by preferencesDataStore(name = "user_prefs_wod_wise")

class UserPreferences @Inject constructor(
    @IO private val ioDispatcher: CoroutineDispatcher,
    private val context: Context,
) {
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DARK_MODE_KEY] ?: false
        }

    private val userEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[EMAIL_KEY]
        }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        withContext(ioDispatcher) {
            context.dataStore.edit { preferences ->
                preferences[IS_DARK_MODE_KEY] = isDarkMode
            }
        }
    }

    suspend fun saveUserEmail(email: String) {
        withContext(ioDispatcher) {
            context.dataStore.edit { preferences ->
                preferences[EMAIL_KEY] = email
            }
        }
    }

    fun getEmail(): Flow<String?> {
        return userEmail
    }

    suspend fun clearUserEmail() {
        withContext(ioDispatcher) {
            context.dataStore.edit { preferences ->
                preferences.remove(EMAIL_KEY)
            }
        }
    }
}

private val EMAIL_KEY = stringPreferencesKey("user_email_wod_wise")
private val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode_wod_wise")
