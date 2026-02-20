package com.tourism.kavora.di

import com.tourism.domain.repository.SettingsRepository
import com.tourism.domain.usecase.CompleteOnboardingUseCase
import com.tourism.domain.usecase.ObserveAppConfigUseCase
import com.tourism.domain.usecase.UpdateThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideObserveAppConfigUseCase(
        repository: SettingsRepository
    ): ObserveAppConfigUseCase {
        return ObserveAppConfigUseCase(repository)
    }

    @Provides
    fun provideUpdateThemeUseCase(
        repository: SettingsRepository
    ): UpdateThemeUseCase {
        return UpdateThemeUseCase(repository)
    }

    @Provides
    fun provideCompleteOnboardingUseCase(
        repository: SettingsRepository
    ): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }
}