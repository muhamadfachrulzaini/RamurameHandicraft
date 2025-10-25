# Contributing Guide

Terima kasih atas minat Anda untuk berkontribusi pada Ramurame Handicraft App!

## Development Setup

1. **Prerequisites**
   - Android Studio Hedgehog (2023.1.1) or later
   - JDK 17
   - Git

2. **Clone & Setup**
   ```bash
   git clone <repository-url>
   cd RamurameHandicraft
   ```

3. **Open in Android Studio**
   - Open project
   - Wait for Gradle sync
   - Run on emulator/device

## Code Style

### Kotlin Style Guide
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable/function names
- Add KDoc comments for public APIs

### Example
```kotlin
/**
 * Calculates the total price of order items
 *
 * @param items List of order items to calculate
 * @return Total price in Rupiah
 */
fun calculateTotalPrice(items: List<OrderItem>): Long {
    return items.sumOf { it.subtotal }
}
```

## Architecture Guidelines

### Adding New Feature

1. **Domain Layer First**
   ```kotlin
   // 1. Create domain model
   data class NewFeature(...)

   // 2. Create use case
   class GetNewFeatureUseCase @Inject constructor(...)

   // 3. Update repository interface
   interface Repository {
       suspend fun getNewFeature(): NewFeature
   }
   ```

2. **Data Layer**
   ```kotlin
   // 1. Create entity
   @Entity data class NewFeatureEntity(...)

   // 2. Add DAO methods
   @Dao interface FeatureDao { ... }

   // 3. Implement repository
   class RepositoryImpl @Inject constructor(...) : Repository
   ```

3. **Presentation Layer**
   ```kotlin
   // 1. Create ViewModel
   class NewFeatureViewModel @Inject constructor(...)

   // 2. Define UI State
   data class NewFeatureUiState(...)

   // 3. Create Composable
   @Composable fun NewFeatureScreen(...)
   ```

### File Naming Convention
- **Screens**: `[Name]Screen.kt`
- **ViewModels**: `[Name]ViewModel.kt`
- **UseCases**: `[Action][Entity]UseCase.kt`
- **Tests**: `[Class]Test.kt`

## Testing

### Write Tests For
- ✅ Domain models
- ✅ Use cases
- ✅ Utility functions
- ✅ ViewModels (future)

### Test Example
```kotlin
@Test
fun `should calculate subtotal correctly`() {
    val item = OrderItem("ID", "Product", 3, 20000)
    assertEquals(60000L, item.subtotal)
}
```

### Run Tests
```bash
./gradlew test
```

## Git Workflow

### Branch Naming
- Feature: `feature/feature-name`
- Bugfix: `bugfix/bug-description`
- Hotfix: `hotfix/critical-fix`

### Commit Messages
```
type(scope): subject

body

footer
```

**Types**:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation
- `style`: Code style (formatting)
- `refactor`: Code refactoring
- `test`: Adding tests
- `chore`: Build/tool changes

**Example**:
```
feat(orders): add filter by date range

- Add date range picker
- Implement filter logic in ViewModel
- Update OrdersScreen UI

Closes #123
```

## Pull Request Process

1. **Before PR**
   - [ ] Code compiles without errors
   - [ ] All tests pass
   - [ ] No lint warnings
   - [ ] Code formatted correctly

2. **PR Description**
   - Describe what changed
   - Why the change is needed
   - Screenshots for UI changes
   - Related issue numbers

3. **Review Process**
   - Wait for review
   - Address feedback
   - Update as needed

## Code Review Checklist

### Functionality
- [ ] Feature works as expected
- [ ] Edge cases handled
- [ ] Error handling present

### Code Quality
- [ ] Follows architecture
- [ ] Clean and readable
- [ ] No code duplication
- [ ] Proper naming

### Testing
- [ ] Tests included
- [ ] Tests pass
- [ ] Coverage adequate

### Documentation
- [ ] Code comments where needed
- [ ] README updated if needed
- [ ] Architecture docs updated

## Common Patterns

### ViewModel Pattern
```kotlin
@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val data = useCase()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    data = data
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
```

### Screen Pattern
```kotlin
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(...) }
    ) { padding ->
        when {
            uiState.isLoading -> LoadingIndicator()
            uiState.error != null -> ErrorMessage()
            else -> FeatureContent(uiState)
        }
    }
}
```

## Resources

### Learning
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [MVVM Pattern](https://developer.android.com/topic/architecture)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

### Tools
- [Android Studio](https://developer.android.com/studio)
- [Kotlin Plugin](https://kotlinlang.org/docs/releases.html)

## Getting Help

- Open an issue for bugs
- Start a discussion for questions
- Check existing issues first

## License

By contributing, you agree that your contributions will be licensed under the same license as the project.

---

Thank you for contributing! 🎉
