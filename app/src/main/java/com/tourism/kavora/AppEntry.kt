package com.tourism.kavora

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.tourism.core.navigation.AppDestinations
import com.tourism.core.navigation.AppNavGraph
import com.tourism.core.navigation.Navigator
import com.tourism.kavora.navigation.AppNavigation
import com.tourism.kavora.navigation.NavigationOrchestrator

@Composable
fun AppEntry(appState: AppUiState) {
    var splashFinished by remember { mutableStateOf(false) }
    var navigator: Navigator? by remember { mutableStateOf(null) }

    AppNavGraph(
        startDestination = AppDestinations.Splash,
        featureNavigations = listOf(AppNavigation),
        onNavigationReady = { nav ->
            navigator = nav
        }
    )
    
    // Handle navigation orchestration outside of NavHost builder
    navigator?.let { navInstance ->
        NavigationOrchestrator(
            appState = appState,
            navigator = navInstance,
            splashFinished = splashFinished
        )
    }
    
    // Track when splash finishes using a simple 3s timeout
    androidx.compose.runtime.LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        splashFinished = true
    }
}