package com.tourism.feature_intro

import androidx.compose.runtime.Composable

@Composable
fun IntroRoute(
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    IntroScreen(
        onNextClick = onNext,
        onSkipClick = onSkip
    )
}