# Navigation System Guide

## Quick Start

This app uses a centralized, type-safe navigation system that scales to 500+ screens.

### Adding a New Feature Module

1. **Create destination definitions** in your feature module:
```kotlin
// feature-example/src/main/.../ExampleDestinations.kt
sealed class ExampleDestinations(
    override val route: String,
    override val arguments: List<NamedNavArgument> = emptyList(),
    override val deepLinks: List<String> = emptyList()
) : NavigationDestination {
    data object ExampleList : ExampleDestinations("example/list")
    data object ExampleDetail : ExampleDestinations("example/detail")
}
```

2. **Create FeatureNavigation implementation**:
```kotlin
// feature-example/src/main/.../ExampleFeatureNavigation.kt
object ExampleFeatureNavigation : FeatureNavigation {
    override val baseRoute: String = "example"
    
    override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
        composable(ExampleDestinations.ExampleList) {
            ExampleListScreen()
        }
        composable(ExampleDestinations.ExampleDetail) {
            ExampleDetailScreen()
        }
    }
}
```

3. **Register in AppNavigation**:
```kotlin
// app/src/main/.../navigation/AppNavigation.kt
override fun NavGraphBuilder.registerDestinations(navigator: Navigator) {
    // ... existing registrations
    ExampleFeatureNavigation.registerDestinations(navigator)
}
```

4. **Use Navigator in screens**:
```kotlin
@Composable
fun ExampleListScreen() {
    val navigator = navigator()
    
    Button(onClick = { 
        navigator.navigate(ExampleDestinations.ExampleDetail) 
    }) {
        Text("Go to Detail")
    }
}
```

## Architecture Benefits

✅ **Type Safety**: Compile-time route checking  
✅ **Modularity**: Each feature manages its own routes  
✅ **Scalability**: Handles 500+ screens efficiently  
✅ **Deep Linking**: Built-in support  
✅ **Testability**: Navigator interface is easily mockable  
✅ **Maintainability**: Centralized navigation logic  

## File Structure

```
core/
  └── navigation/
      ├── NavigationDestination.kt    # Base interface
      ├── Navigator.kt                # Navigation interface
      ├── FeatureNavigation.kt        # Feature registration interface
      ├── AppNavGraph.kt              # Main graph builder
      ├── AppDestinations.kt          # Top-level destinations
      ├── NavigationProvider.kt      # CompositionLocal provider
      └── NestedNavGraph.kt          # Nested graph helpers

feature-*/
  └── [feature-name]/
      ├── [Feature]Destinations.kt      # Feature destinations
      └── [Feature]FeatureNavigation.kt # Feature registration

app/
  └── navigation/
      └── AppNavigation.kt            # App-level registration
```

## Examples

See:
- `feature-splash/SplashFeatureNavigation.kt` - Example feature navigation
- `core/navigation/README.md` - Detailed documentation
