package com.tourism.domain.usecase



import com.tourism.core.model.AppConfig
import com.tourism.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import com.tourism.domain.utils.Result

class ObserveAppConfigUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Result<AppConfig>> {
        return repository.observeAppConfig()
    }
}