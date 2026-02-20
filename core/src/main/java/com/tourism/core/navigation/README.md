# Centralized Navigation System

This is a production-ready, scalable navigation system designed to handle 500+ screens smoothly.

## Architecture Overview

The navigation system is built on three main principles:
1. **Type Safety**: All routes are defined as sealed classes/interfaces
2. **Modularity**: Each feature module registers its own routes independently
3. **Centralization**: All navigation logic flows through a single Navigator interface

## Core Components

### 1. NavigationDestination
Base interface for all navigation destinations. Defines:
- `route`: The route pattern (e.g., "profile/{userId}")
- `arguments`: Optional navigation arguments
- `deepLinks`: Optional deep link patterns

### 2. Navigator
Centralized navigation interface providing:
- Type-safe navigation methods
- Back stack management
- Current route tracking

### 3. FeatureNavigation
Interface that feature modules implement to register their routes.

### 4. AppNavGraph
Main navigation graph builder that:
- Registers all feature modules
- Provides Navigator via CompositionLocal
- Handles app-level navigation setup

## Usage Guide

### Step 1: Define Your Feature Destinations

In your feature module, create a sealed class for destinations:

```kotlin
// feature-profile/src/main/.../ProfileDestinations.kt
sealed class ProfileDestinations(
    override val route: String,
    override val arguments: List<NamedNavArgument> = emptyList(),
    override val deepLinks: List<String> = emptyList()
) : NavigationDestination {
    
    data object ProfileList : ProfileDestinations(
        route = "profile/list",
        deepLinks = listOf("kavora://profile/list")
    )
    
    data class ProfileDetail(
        val userId: String = "{userId}"
    ) : ProfileDestinations(
        route = "profile/detail/{userId}",
        arguments = listOf(
            createNavArgument("userId", NavType.StringType)
        ),
        deepLinks = listOf("kavora://profile/detail/{userId}")
    ) {
        companion object {
            fun createRoute(userId: String) = "profile/detail/$userId"
        }
    }
}
```

### Step 2: Create FeatureNavigation Implementation

```kotlin
// feature-profile/src/main/.../ProfileFeatureNavigation.kt
object ProfileFeatureNavigation : FeatureNavigation {
    override val baseRoute: String = "profile"

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        composable(ProfileDestinations.ProfileList) {
            ProfileListScreen()
        }
        
        composable(ProfileDestinations.ProfileDetail) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ProfileDetailScreen(userId = userId)
        }
    }
}
```

### Step 3: Register Feature in App Navigation

```kotlin
// app/src/main/.../navigation/AppNavigation.kt
object AppNavigation : FeatureNavigation {
    override val baseRoute: String = "app"

    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        SplashFeatureNavigation.registerDestinations(navigator)
        ProfileFeatureNavigation.registerDestinations(navigator)
        // ... other features
    }
}
```

### Step 4: Use Navigator in Your Screens

```kotlin
@Composable
fun ProfileListScreen() {
    val navigator = navigator() // Get from CompositionLocal
    
    Button(
        onClick = {
            navigator.navigate(ProfileDestinations.ProfileDetail(userId = "123"))
        }
    ) {
        Text("View Profile")
    }
}
```

## Advanced Features

### Nested Navigation Graphs

For complex flows, use nested graphs:

```kotlin
nestedNavGraph(
    route = "auth",
    startDestination = AuthDestinations.Login
) {
    composable(AuthDestinations.Login) { LoginScreen() }
    composable(AuthDestinations.Register) { RegisterScreen() }
}
```

### Type-Safe Arguments

```kotlin
data class ProductDetail(
    val productId: String = "{productId}",
    val categoryId: String = "{categoryId}"
) : NavigationDestination {
    override val route = "product/{productId}/{categoryId}"
    override val arguments = listOf(
        createNavArgument("productId", NavType.StringType),
        createNavArgument("categoryId", NavType.StringType)
    )
    
    companion object {
        fun createRoute(productId: String, categoryId: String) = 
            "product/$productId/$categoryId"
    }
}
```

### Deep Linking

Deep links are automatically registered:

```kotlin
data object ProductList : NavigationDestination {
    override val route = "product/list"
    override val deepLinks = listOf(
        "kavora://product/list",
        "https://kavora.com/product/list"
    )
}
```

## Best Practices

1. **One FeatureNavigation per feature module**: Keep navigation registration scoped to the feature
2. **Use sealed classes**: Provides type safety and exhaustive when expressions
3. **Define routes in core module**: For shared destinations, define in `AppDestinations`
4. **Use companion objects**: For creating routes with arguments
5. **Keep navigation logic centralized**: Use NavigationOrchestrator for app-level navigation logic

## Scaling to 500+ Screens

This system scales well because:
- **Modular registration**: Each feature module manages its own routes
- **Lazy loading**: Routes are only registered when feature modules are included
- **Type safety**: Compile-time checks prevent navigation errors
- **Separation of concerns**: Navigation logic is separate from UI logic

## Migration from Old System

1. Replace `Routes` object with sealed class destinations
2. Implement `FeatureNavigation` for each feature module
3. Update `AppNavGraph` to use new system
4. Replace direct `NavController` usage with `Navigator` interface
