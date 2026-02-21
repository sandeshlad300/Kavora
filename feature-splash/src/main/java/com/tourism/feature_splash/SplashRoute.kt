package com.tourism.feature_splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Splash screen route composable.
 * This can optionally use Navigator from CompositionLocal if needed.
 */
@Composable
fun SplashRoute(
    onFinished: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isFinished by viewModel.isFinished.collectAsState()

    // Animate progress independently
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
    }

    // Navigate only once
    LaunchedEffect(isFinished) {
        if (isFinished) {
            onFinished()
        }
    }

    SplashScreen(progress = progress.value)
}