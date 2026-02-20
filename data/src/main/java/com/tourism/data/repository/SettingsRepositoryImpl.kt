package com.tourism.data.repository


import com.tourism.core.model.AppConfig
import com.tourism.core.model.ThemeMode
import com.tourism.data.datastore.PreferenceManager
import com.tourism.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import com.tourism.domain.utils.Result

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : SettingsRepository {

    override fun observeAppConfig(): Flow<Result<AppConfig>> {
        return preferenceManager.appConfigFlow
            .map { config: AppConfig ->
                Result.Success(config) as Result<AppConfig>
            }
            .catch { e: Throwable ->
                emit(Result.Error("Failed to load app config", e))
            }
    }

    override suspend fun updateTheme(themeMode: ThemeMode) {
        preferenceManager.setTheme(themeMode)
    }

    override suspend fun completeOnboarding() {
        preferenceManager.setOnboardingCompleted()
    }
}