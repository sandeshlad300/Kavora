package com.tourism.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

/**
 * Centralized navigation interface that provides type-safe navigation methods.
 * This should be provided via dependency injection and used throughout the app.
 */
interface Navigator {
    /**
     * Navigate to a destination.
     */
    fun navigate(
        destination: NavigationDestination,
        builder: NavOptionsBuilder.() -> Unit = {}
    )

    /**
     * Navigate to a route string (for dynamic navigation).
     */
    fun navigateToRoute(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = {}
    )

    /**
     * Navigate back.
     */
    fun navigateBack()

    /**
     * Pop back stack to a specific destination.
     */
    fun popBackStack(
        destination: NavigationDestination,
        inclusive: Boolean = false
    ): Boolean

    /**
     * Pop back stack to a specific route.
     */
    fun popBackStackToRoute(
        route: String,
        inclusive: Boolean = false
    ): Boolean

    /**
     * Get the current destination route.
     */
    fun getCurrentRoute(): String?

    /**
     * Check if a destination is the current destination.
     */
    fun isCurrentDestination(destination: NavigationDestination): Boolean
}

/**
 * Default implementation of Navigator using NavController.
 */
class NavigatorImpl(
    private val navController: NavController
) : Navigator {

    override fun navigate(
        destination: NavigationDestination,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        navController.navigate(destination.route, builder)
    }

    override fun navigateToRoute(
        route: String,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        navController.navigate(route, builder)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun popBackStack(
        destination: NavigationDestination,
        inclusive: Boolean
    ): Boolean {
        return navController.popBackStack(destination.route, inclusive)
    }

    override fun popBackStackToRoute(
        route: String,
        inclusive: Boolean
    ): Boolean {
        return navController.popBackStack(route, inclusive)
    }

    override fun getCurrentRoute(): String? {
        return navController.currentDestination?.route
    }

    override fun isCurrentDestination(destination: NavigationDestination): Boolean {
        return getCurrentRoute() == destination.route
    }
}
