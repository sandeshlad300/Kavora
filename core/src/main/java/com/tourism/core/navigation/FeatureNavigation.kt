package com.tourism.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navDeepLink
import androidx.navigation.compose.composable

/**
 * Interface that feature modules should implement to register their navigation destinations.
 * This allows each feature module to define its own routes independently.
 */
interface FeatureNavigation {
    /**
     * The base route for this feature module (e.g., "home", "profile", "settings").
     * This is used for nested navigation graphs.
     */
    val baseRoute: String

    /**
     * Register all destinations for this feature module.
     * This method is called during NavHost setup to register routes.
     */
    fun NavGraphBuilder.registerDestinations(
        navigator: Navigator
    )
}

/**
 * Extension function to register a destination with type-safe navigation.
 *
 * The underlying Navigation-Compose API expects:
 * @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
 *
 * To keep call sites simple, we accept:
 * @Composable (NavBackStackEntry) -> Unit
 * and adapt it.
 */
fun NavGraphBuilder.composable(
    destination: NavigationDestination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks.map { pattern ->
            navDeepLink { uriPattern = pattern }
        }
    ) { backStackEntry ->
        content(backStackEntry)
    }
}
