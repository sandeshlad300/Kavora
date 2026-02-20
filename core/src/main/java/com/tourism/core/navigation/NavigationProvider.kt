package com.tourism.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

/**
 * CompositionLocal for Navigator to be accessed throughout the composable tree.
 * This allows any composable to access navigation without prop drilling.
 */
val LocalNavigator = compositionLocalOf<Navigator> {
    error("No Navigator provided. Make sure to provide Navigator in your NavGraph.")
}

/**
 * Provides Navigator to the composition tree.
 * This should be called at the root of your navigation graph.
 */
@Composable
fun ProvideNavigator(
    navigator: Navigator,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalNavigator provides navigator) {
        content()
    }
}

/**
 * Extension function to get Navigator from CompositionLocal.
 */
@Composable
fun navigator(): Navigator = LocalNavigator.current
