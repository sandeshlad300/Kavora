package com.tourism.feature_onboarding

import androidx.navigation.NavGraphBuilder
import com.tourism.core.navigation.FeatureNavigation
import com.tourism.core.navigation.NavigationDestination
import com.tourism.core.navigation.Navigator
import com.tourism.core.navigation.composable

object OnboardingFeatureNavigation : FeatureNavigation {
    override val baseRoute: String = OnboardingDestination.route

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        composable(OnboardingDestination) { _ ->
            OnboardingScreen(
                onFinish = {
                    // Later: navigator.navigate(AppDestinations.Home) etc.
                }
            )
        }
    }
}

object OnboardingDestination : NavigationDestination {
    override val route: String = "onboarding"
}
