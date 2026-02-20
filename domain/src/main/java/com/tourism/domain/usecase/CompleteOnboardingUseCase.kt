package com.tourism.domain.usecase


import com.tourism.domain.repository.SettingsRepository

class CompleteOnboardingUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke() {
        repository.completeOnboarding()
    }
}