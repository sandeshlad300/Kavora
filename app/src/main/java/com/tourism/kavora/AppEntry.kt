package com.tourism.kavora

import androidx.compose.runtime.Composable
import com.tourism.kavora.navigation.NavGraph
import com.tourism.kavora.navigation.Routes

@Composable
fun AppEntry(appState: AppUiState) {

    val startDestination =
        if (!appState.onboardingCompleted)
            Routes.ONBOARDING
        else
            Routes.HOME

    NavGraph(startDestination)
}