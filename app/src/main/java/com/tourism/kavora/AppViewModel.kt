package com.tourism.kavora

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.data.datastore.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.tourism.core.model.UserState


@HiltViewModel
class AppViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    val appUiState: StateFlow<AppUiState> =
        preferenceManager.appConfigFlow
            .map { config ->

                AppUiState(
                    themeMode = config.themeMode,
                    languageCode = config.languageCode,
                    onboardingCompleted = config.onboardingCompleted,
                    userState = UserState.Guest
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = AppUiState()
            )
}