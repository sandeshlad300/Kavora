package com.tourism.core.model


data class AppConfig(
    val themeMode: ThemeMode,
    val languageCode: String,
    val onboardingCompleted: Boolean
)