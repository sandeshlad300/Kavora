package com.tourism.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * Main navigation graph builder for the entire app.
 * This centralizes all navigation setup and allows feature modules to register their routes.
 *
 * Usage:
 * ```
 * AppNavGraph(
 *     appState = appState,
 *     featureNavigations = listOf(
 *         SplashFeatureNavigation(),
 *         HomeFeatureNavigation(),
 *         // ... other feature navigations
 *     )
 * )
 * ```
 */
@Composable
fun AppNavGraph(
    startDestination: NavigationDestination = AppDestinations.Splash,
    featureNavigations: List<FeatureNavigation> = emptyList(),
    onNavigationReady: (Navigator) -> Unit = {}
) {
    val navController = rememberNavController()
    val navigator = remember { NavigatorImpl(navController) }

    // Notify parent that navigation is ready
    LaunchedEffect(navigator) {
        onNavigationReady(navigator)
    }

    ProvideNavigator(navigator = navigator) {
        NavHost(
            navController = navController,
            startDestination = startDestination.route
        ) {
            // Register all feature module destinations
            featureNavigations.forEach { featureNav ->
                // Call FeatureNavigation's NavGraphBuilder extension with both receivers in scope:
                // - dispatch receiver: featureNav
                // - extension receiver: this (NavGraphBuilder from NavHost)
                with(featureNav) {
                    registerDestinations(navigator)
                }
            }
        }
    }
}

// Note: we intentionally expose a single AppNavGraph overload.
// Callers should always provide featureNavigations explicitly.
