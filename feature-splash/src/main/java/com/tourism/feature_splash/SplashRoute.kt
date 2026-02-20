package com.tourism.feature_splash

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Splash screen route composable.
 * This can optionally use Navigator from CompositionLocal if needed.
 */
@Composable
fun SplashRoute(
    onFinished: () -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isFinished by viewModel.isFinished.collectAsState()

    if (isFinished) {
        LaunchedEffect(Unit) {
            onFinished()
        }
    }
    SplashScreen()
}