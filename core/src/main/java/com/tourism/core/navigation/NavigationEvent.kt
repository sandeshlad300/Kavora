package com.tourism.core.navigation

/**
 * Sealed class representing navigation events that can be emitted from feature modules.
 * This allows feature modules to communicate navigation intent without direct coupling.
 *
 * Example usage:
 * ```
 * NavigationEventBus.emit(SplashFinished)
 * ```
 */
sealed class NavigationEvent {
    /**
     * Emitted when splash screen finishes.
     */
    data object SplashFinished : NavigationEvent()

    /**
     * Emitted when user completes onboarding.
     */
    data object OnboardingCompleted : NavigationEvent()

    /**
     * Emitted when user completes intro.
     */
    data object IntroCompleted : NavigationEvent()

    /**
     * Custom navigation event - navigate to a specific destination.
     */
    data class NavigateTo(val destination: NavigationDestination) : NavigationEvent()

    /**
     * Custom navigation event - navigate back.
     */
    data object NavigateBack : NavigationEvent()
}

/**
 * Simple event bus for navigation events.
 * In a production app, you might want to use a more sophisticated event system
 * like Kotlin Flow, SharedFlow, or a state management library.
 */
object NavigationEventBus {
    private val listeners = mutableListOf<(NavigationEvent) -> Unit>()

    /**
     * Subscribe to navigation events.
     */
    fun subscribe(listener: (NavigationEvent) -> Unit) {
        listeners.add(listener)
    }

    /**
     * Unsubscribe from navigation events.
     */
    fun unsubscribe(listener: (NavigationEvent) -> Unit) {
        listeners.remove(listener)
    }

    /**
     * Emit a navigation event.
     */
    fun emit(event: NavigationEvent) {
        listeners.forEach { it(event) }
    }
}
