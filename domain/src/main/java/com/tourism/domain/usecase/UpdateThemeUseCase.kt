package com.tourism.domain.usecase


import com.tourism.core.model.ThemeMode
import com.tourism.domain.repository.SettingsRepository

class UpdateThemeUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        repository.updateTheme(themeMode)
    }
}