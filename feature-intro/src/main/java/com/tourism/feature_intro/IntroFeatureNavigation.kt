package com.tourism.feature_intro

import androidx.navigation.NavGraphBuilder
import com.tourism.core.navigation.FeatureNavigation
import com.tourism.core.navigation.NavigationDestination
import com.tourism.core.navigation.Navigator
import com.tourism.core.navigation.composable

/**
 * Feature-intro navigation registration.
 * This owns the actual Intro composable for the "intro" route.
 */
object IntroFeatureNavigation : FeatureNavigation {
    override val baseRoute: String = "intro"

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        composable(IntroDestination) { _ ->
            IntroScreen(
                onContinue = {
                    // Later you can navigate to onboarding/home using navigator
                }
            )
        }
    }
}

/**
 * Intro screen destination definition.
 * Route string matches AppDestinations.Intro.route ("intro").
 */
object IntroDestination : NavigationDestination {
    override val route: String = "intro"
}

