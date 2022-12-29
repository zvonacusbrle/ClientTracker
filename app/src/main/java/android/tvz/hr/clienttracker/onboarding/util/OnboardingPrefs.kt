package android.tvz.hr.clienttracker.onboarding.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = OnboardingPrefs.ON_BOARDING_PREFS
)

class OnboardingPrefs(val context: Context) {
    companion object {
        const val ON_BOARDING_PREFS = "on_boarding_prefs"
    }

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
    }

    private val dataStore = context.dataStore

    suspend fun saveOnboarding(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun checkOnBoardingState() : Flow<Boolean> {
        return dataStore.data
            .map {
                preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }

    }

}





