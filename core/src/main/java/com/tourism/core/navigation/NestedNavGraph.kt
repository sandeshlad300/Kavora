package com.tourism.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation

/**
 * Helper for creating nested navigation graphs.
 * This is useful for organizing complex feature flows (e.g., authentication flow, onboarding flow).
 *
 * Example usage:
 * ```
 * nestedNavGraph(
 *     route = "auth",
 *     startDestination = AuthDestinations.Login,
 *     destinations = listOf(
 *         AuthDestinations.Login,
 *         AuthDestinations.Register,
 *         AuthDestinations.ForgotPassword
 *     )
 * ) {
 *     // Register destinations
 * }
 * ```
 */
fun NavGraphBuilder.nestedNavGraph(
    route: String,
    startDestination: NavigationDestination,
    destinations: List<NavigationDestination> = emptyList(),
    deepLinks: List<String> = emptyList(),
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = route,
        startDestination = startDestination.route,
        deepLinks = deepLinks.map { 
            androidx.navigation.navDeepLink { uriPattern = it }
        }
    ) {
        builder()
    }
}

/**
 * Extension to register a nested graph with a FeatureNavigation.
 */
fun NavGraphBuilder.nestedFeatureGraph(
    featureNav: FeatureNavigation,
    startDestination: NavigationDestination,
    navigator: Navigator
) {
    nestedNavGraph(
        route = featureNav.baseRoute,
        startDestination = startDestination
    ) {
        // Call FeatureNavigation's NavGraphBuilder extension explicitly with both receivers:
        // - dispatch receiver: featureNav
        // - extension receiver: this@nestedFeatureGraph (NavGraphBuilder)
        featureNav.run {
            this@nestedFeatureGraph.registerDestinations(navigator)
        }
    }
}
