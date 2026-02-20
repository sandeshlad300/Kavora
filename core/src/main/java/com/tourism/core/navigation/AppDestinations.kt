package com.tourism.core.navigation

import androidx.navigation.NamedNavArgument


sealed class AppDestinations(
    override val route: String,
    override val arguments: List<NamedNavArgument> = emptyList(),
    override val deepLinks: List<String> = emptyList()
) : NavigationDestination {

    /**
     * Splash screen - shown on app launch
     */
    data object Splash : AppDestinations(
        route = "splash",
        deepLinks = listOf("kavora://splash")
    )

    /**
     * Intro/Onboarding flow
     */
    data object Intro : AppDestinations(
        route = "intro",
        deepLinks = listOf("kavora://intro")
    )

    /**
     * Onboarding screens
     */
    data object Onboarding : AppDestinations(
        route = "onboarding",
        deepLinks = listOf("kavora://onboarding")
    )

    /**
     * Main home screen
     */
    data object Home : AppDestinations(
        route = "home",
        deepLinks = listOf("kavora://home")
    )

    /**
     * Authentication flow (if needed)
     */
    data object Auth : AppDestinations(
        route = "auth",
        deepLinks = listOf("kavora://auth")
    )

    /**
     * Example: Profile screen with user ID argument
     * Uncomment and modify as needed:
     */
    /*
    data class Profile(
        val userId: String = "{userId}"
    ) : AppDestinations(
        route = "profile/{userId}",
        arguments = listOf(
            createNavArgument("userId", NavType.StringType)
        ),
        deepLinks = listOf("kavora://profile/{userId}")
    ) {
        companion object {
            fun createRoute(userId: String) = "profile/$userId"
        }
    }
    */
}
