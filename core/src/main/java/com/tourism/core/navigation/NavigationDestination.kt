package com.tourism.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Base interface for all navigation destinations in the app.
 * This provides a type-safe way to define routes with optional arguments.
 *
 * Each feature module should define its destinations as sealed classes/objects
 * implementing this interface.
 */
interface NavigationDestination {
    /**
     * The route pattern for this destination.
     * Use {argName} for arguments, e.g., "profile/{userId}"
     */
    val route: String

    /**
     * Optional list of navigation arguments this destination requires.
     */
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    /**
     * Optional deep link patterns for this destination.
     */
    val deepLinks: List<String>
        get() = emptyList()
}

/**
 * Helper function to create a NamedNavArgument for common types.
 */
fun createNavArgument(
    name: String,
    type: NavType<*>,
    nullable: Boolean = false,
    defaultValue: Any? = null
): NamedNavArgument {
    return navArgument(name) {
        this.type = type
        this.nullable = nullable
        defaultValue?.let { this.defaultValue = it }
    }
}
