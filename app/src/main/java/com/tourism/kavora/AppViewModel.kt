package com.tourism.kavora

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.core.model.AppConfig
import com.tourism.core.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.tourism.core.model.UserState
import com.tourism.domain.usecase.CompleteOnboardingUseCase
import com.tourism.domain.usecase.ObserveAppConfigUseCase
import com.tourism.domain.usecase.UpdateThemeUseCase
import kotlinx.coroutines.launch
import com.tourism.domain.utils.Result


@HiltViewModel
class AppViewModel @Inject constructor(
    private val observeAppConfig: ObserveAppConfigUseCase,
    private val updateTheme: UpdateThemeUseCase,
    private val completeOnboarding: CompleteOnboardingUseCase
) : ViewModel() {

    val appUiState: StateFlow<AppUiState> =
        observeAppConfig()
            .map { result ->

                when (result) {

                    is Result.Success -> {
                        val config = result.data

                        AppUiState(
                            themeMode = config.themeMode,
                            languageCode = config.languageCode,
                            onboardingCompleted = config.onboardingCompleted,
                            userState = UserState.Guest
                        )
                    }

                    is Result.Error -> {
                        AppUiState() // fallback safe state
                    }
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                AppUiState()
            )

    fun changeTheme(themeMode: ThemeMode) {
        viewModelScope.launch {
            updateTheme(themeMode)
        }
    }

    fun finishOnboarding() {
        viewModelScope.launch {
            completeOnboarding()
        }
    }
}