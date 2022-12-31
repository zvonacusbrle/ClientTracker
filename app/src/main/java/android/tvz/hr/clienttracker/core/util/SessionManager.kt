package android.tvz.hr.clienttracker.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


class SessionManager(val context: Context){

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SESSION_MANAGER)

    suspend fun updateSession(token: String, name: String){
        val jwtTokenKey = stringPreferencesKey(JWT_TOKEN_KEY)
        val nameKey = stringPreferencesKey(NAME_KEY)
        context.dataStore.edit { preferences ->
            preferences[jwtTokenKey] = token
            preferences[nameKey] = name
        }
    }

}

const val SESSION_MANAGER = "session_manager"
const val JWT_TOKEN_KEY = "JWT_TOKEN_KEY"
const val NAME_KEY = "JWT_NAME_KEY"