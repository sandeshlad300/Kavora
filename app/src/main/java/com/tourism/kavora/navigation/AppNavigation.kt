package com.tourism.kavora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.tourism.core.navigation.AppDestinations
import com.tourism.core.navigation.FeatureNavigation
import com.tourism.core.navigation.Navigator
import com.tourism.core.navigation.composable
import com.tourism.feature_splash.SplashFeatureNavigation
import com.tourism.feature_intro.IntroFeatureNavigation
import com.tourism.kavora.AppUiState

/**
 * Main app navigation setup.
 * This registers all feature modules and handles app-level navigation logic.
 */
object AppNavigation : FeatureNavigation {
    override val baseRoute: String = "app"

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        // Register splash feature
        with(SplashFeatureNavigation) {
            // extension receiver = this@registerDestinations (NavGraphBuilder)
            this@registerDestinations.registerDestinations(navigator)
        }

        // Register intro feature
        with(IntroFeatureNavigation) {
            this@registerDestinations.registerDestinations(navigator)
        }

        // Placeholder screens for other destinations
        composable(AppDestinations.Onboarding) { _ ->
            androidx.compose.material3.Text("Onboarding Screen - Coming Soon")
        }

        composable(AppDestinations.Home) { _ ->
            androidx.compose.material3.Text("Home Screen - Coming Soon")
        }
    }
}

/**
 * Navigation orchestrator that handles app-level navigation logic.
 * For now: after splash finishes, always go to Intro.
 */
@Composable
fun NavigationOrchestrator(
    appState: AppUiState,
    navigator: Navigator,
    splashFinished: Boolean
) {
    // We only care that splashFinished became true once
    androidx.compose.runtime.LaunchedEffect(splashFinished) {
        if (!splashFinished) return@LaunchedEffect

        navigator.navigate(AppDestinations.Intro) {
            popUpTo(AppDestinations.Splash.route) { inclusive = true }
            launchSingleTop = true
        }
    }
}
