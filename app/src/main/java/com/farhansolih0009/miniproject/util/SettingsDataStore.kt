package com.farhansolih0009.miniproject.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "settings_preference"
)
class SettingsDataStore(private val context: Context) {
    companion object {
        private val IS_LIST = booleanPreferencesKey("is_list")
        private val LOGGED_IN_USER = stringPreferencesKey("logged_in_user")
    }

    val layoutFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LIST] ?: true
    }

    val loggedInUserFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[LOGGED_IN_USER]
    }

    suspend fun saveLayout(isList: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LIST] = isList
        }
    }

    suspend fun saveLoginStatus(username: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGGED_IN_USER] = username
        }
    }

    suspend fun clearLoginStatus() {
        context.dataStore.edit { preferences ->
            preferences.remove(LOGGED_IN_USER)
        }
    }
}