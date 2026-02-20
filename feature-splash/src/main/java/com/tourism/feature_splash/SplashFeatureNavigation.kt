package com.tourism.feature_splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import com.tourism.core.navigation.FeatureNavigation
import com.tourism.core.navigation.NavigationDestination
import com.tourism.core.navigation.Navigator
import com.tourism.core.navigation.composable

/**
 * Splash feature navigation registration.
 * This implements FeatureNavigation to register splash screen routes.
 */
object SplashFeatureNavigation : FeatureNavigation {
    override val baseRoute: String = "splash"

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        composable(SplashDestination) { _ ->
            SplashRouteWithCallback(
                onFinished = {
                    // This callback is called when splash finishes
                    // The actual navigation is handled by NavigationOrchestrator
                }
            )
        }
    }
}

/**
 * Internal composable that tracks splash completion.
 */
@Composable
private fun SplashRouteWithCallback(
    onFinished: () -> Unit
) {
    var hasFinished by remember { mutableStateOf(false) }
    
    SplashRoute(
        onFinished = {
            if (!hasFinished) {
                hasFinished = true
                onFinished()
            }
        }
    )
}

/**
 * Splash screen destination definition.
 */
object SplashDestination : NavigationDestination {
    override val route: String = "splash"
    override val deepLinks: List<String> = listOf("kavora://splash")
}
