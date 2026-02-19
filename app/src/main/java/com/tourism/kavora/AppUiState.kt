package com.tourism.kavora

import com.tourism.core.model.ThemeMode
import com.tourism.core.model.UserState

data class AppUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val languageCode: String = "en",
    val onboardingCompleted: Boolean = false,
    val userState: UserState = UserState.Guest
)