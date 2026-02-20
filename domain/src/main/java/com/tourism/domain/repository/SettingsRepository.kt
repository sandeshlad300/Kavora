package com.tourism.domain.repository


import com.tourism.core.model.AppConfig
import com.tourism.core.model.ThemeMode
import com.tourism.domain.utils.Result
import kotlinx.coroutines.flow.Flow


interface SettingsRepository {

    fun observeAppConfig(): Flow<Result<AppConfig>>

    suspend fun updateTheme(themeMode: ThemeMode)

    suspend fun completeOnboarding()
}