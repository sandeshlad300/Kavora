package com.tourism.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.edit
import com.tourism.core.model.AppConfig
import com.tourism.core.model.ThemeMode

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val THEME_KEY = stringPreferencesKey("theme_mode")
        val LANGUAGE_KEY = stringPreferencesKey("language_code")
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")
    }

    val appConfigFlow: Flow<AppConfig> =
        dataStore.data.map { prefs ->

            AppConfig(
                themeMode = ThemeMode.valueOf(
                    prefs[THEME_KEY] ?: ThemeMode.SYSTEM.name
                ),
                languageCode = prefs[LANGUAGE_KEY] ?: "en",
                onboardingCompleted = prefs[ONBOARDING_KEY] ?: false
            )
        }

    suspend fun setTheme(theme: ThemeMode) {
        dataStore.edit { it[THEME_KEY] = theme.name }
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { it[LANGUAGE_KEY] = language }
    }

    suspend fun setOnboardingCompleted() {
        dataStore.edit { it[ONBOARDING_KEY] = true }
    }
}