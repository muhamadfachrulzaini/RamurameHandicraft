# Architecture Documentation

## Clean Architecture + MVVM

Aplikasi Ramurame Handicraft menggunakan Clean Architecture dengan MVVM pattern untuk memastikan kode yang clean, testable, dan maintainable.

## Layer Breakdown

### 1. Presentation Layer (UI)

**Responsibility**: Menampilkan data dan menangani user interaction

**Components**:
- **Composables**: UI components menggunakan Jetpack Compose
- **ViewModels**: Menyimpan UI state dan handle business logic calls
- **UI States**: Data classes yang merepresentasikan state dari UI
- **Navigation**: Mengelola navigasi antar screens

**Key Files**:
```
presentation/
├── dashboard/
│   ├── DashboardScreen.kt       # Dashboard UI
│   └── DashboardViewModel.kt    # Dashboard state management
├── orders/
│   ├── OrdersScreen.kt          # Orders list UI
│   └── OrdersViewModel.kt       # Orders state management
├── detail/
│   ├── OrderDetailScreen.kt     # Order detail UI
│   └── OrderDetailViewModel.kt  # Detail state management
├── navigation/
│   ├── Screen.kt                # Screen routes
│   └── NavGraph.kt              # Navigation graph
└── theme/
    ├── Color.kt                 # Color definitions
    ├── Theme.kt                 # Theme setup
    └── Type.kt                  # Typography
```

**Data Flow** (Unidirectional):
```
User Action → ViewModel → Use Case → Repository
    ↓
UI State Update → Recomposition
```

### 2. Domain Layer (Business Logic)

**Responsibility**: Berisi pure business logic, tidak bergantung pada framework

**Components**:
- **Models**: Domain models yang merepresentasikan business entities
- **Repository Interfaces**: Kontrak untuk data operations
- **Use Cases**: Single-responsibility business operations

**Key Files**:
```
domain/
├── model/
│   ├── Order.kt                 # Order domain model
│   ├── OrderItem.kt             # Order item model
│   ├── OrderStatus.kt           # Status enum
│   ├── Product.kt               # Product catalog
│   └── StatusHistory.kt         # Status history model
├── repository/
│   └── OrderRepository.kt       # Repository interface
└── usecase/
    ├── GetAllOrdersUseCase.kt
    ├── GetOrdersByStatusUseCase.kt
    ├── GetOrderDetailUseCase.kt
    ├── UpdateOrderStatusUseCase.kt
    └── GetDashboardDataUseCase.kt
```

**Principles**:
- **No Android Dependencies**: Pure Kotlin, bisa di-test tanpa Android framework
- **Single Responsibility**: Setiap use case melakukan satu hal
- **Dependency Inversion**: Bergantung pada abstractions (interfaces)

### 3. Data Layer (Data Management)

**Responsibility**: Menyediakan dan mengelola data

**Components**:
- **Entities**: Database models (Room)
- **DAOs**: Data Access Objects untuk database operations
- **Database**: Room database configuration
- **Repository Implementation**: Implementasi dari Repository interface
- **Mappers**: Convert Entity ↔ Domain Model

**Key Files**:
```
data/
├── local/
│   ├── dao/
│   │   └── OrderDao.kt          # Database queries
│   ├── database/
│   │   └── RamurameDatabase.kt  # Room database
│   ├── entity/
│   │   └── OrderEntity.kt       # Database entity
│   ├── converter/
│   │   └── Converters.kt        # Type converters for complex types
│   ├── mapper/
│   │   └── OrderMapper.kt       # Entity ↔ Model conversion
│   ├── DummyDataGenerator.kt    # Generate test data
│   └── DatabaseInitializer.kt   # Database setup
└── repository/
    └── OrderRepositoryImpl.kt   # Repository implementation
```

**Data Flow**:
```
ViewModel → Use Case → Repository Interface
                           ↓
                    Repository Implementation
                           ↓
                    DAO → Room Database
                           ↓
                    SQLite Database
```

## Dependency Injection (Hilt)

**Purpose**: Menyediakan dependencies secara otomatis

**Modules**:

### DatabaseModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides @Singleton
    fun provideDatabase(context: Context): RamurameDatabase

    @Provides @Singleton
    fun provideOrderDao(database: RamurameDatabase): OrderDao
}
```

### RepositoryModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository
}
```

### AppModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun provideContext(@ApplicationContext context: Context): Context
}
```

## State Management

### ViewModel State Pattern
```kotlin
data class DashboardUiState(
    val isLoading: Boolean = false,
    val todayOrdersCount: Int = 0,
    val todayRevenue: Long = 0,
    val recentOrders: List<Order> = emptyList(),
    val error: String? = null
)

class DashboardViewModel @Inject constructor(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // Load data...
        }
    }
}
```

### UI Consumption
```kotlin
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingIndicator()
        uiState.error != null -> ErrorMessage(uiState.error)
        else -> DashboardContent(uiState)
    }
}
```

## Data Persistence

### Room Database

**Entity Definition**:
```kotlin
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val items: String,  // JSON
    val statusHistory: String  // JSON
    // ... other fields
)
```

**DAO Operations**:
```kotlin
@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY orderDate DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Update
    suspend fun updateOrder(order: OrderEntity)
}
```

**Type Converters**:
```kotlin
class Converters {
    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItem> {
        return gson.fromJson(value, listType)
    }
}
```

## Navigation

### Screen Routes
```kotlin
sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Orders : Screen("orders")
    object OrderDetail : Screen("order_detail/{orderId}") {
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}
```

### Navigation Graph
```kotlin
NavHost(navController, startDestination = Screen.Dashboard.route) {
    composable(Screen.Dashboard.route) {
        DashboardScreen(
            onNavigateToOrders = { navController.navigate(Screen.Orders.route) },
            onNavigateToOrderDetail = { id ->
                navController.navigate(Screen.OrderDetail.createRoute(id))
            }
        )
    }
    // ... other routes
}
```

## Testing Strategy

### Unit Tests
- **Domain Models**: Test business logic, calculations
- **Use Cases**: Test business operations
- **Utilities**: Test formatters, helpers

### Integration Tests (Future)
- **Repository**: Test database operations
- **ViewModel**: Test state management

### UI Tests (Future)
- **Screens**: Test user interactions
- **Navigation**: Test navigation flows

## Best Practices Applied

1. **Single Responsibility Principle**: Setiap class memiliki satu tanggung jawab
2. **Dependency Inversion**: Bergantung pada abstractions, bukan concrete implementations
3. **Separation of Concerns**: Setiap layer memiliki concern yang berbeda
4. **Immutability**: Menggunakan data classes yang immutable
5. **Reactive Programming**: Menggunakan Flow untuk reactive data streams
6. **State Management**: Centralized state dengan ViewModel
7. **Error Handling**: Proper error handling di setiap layer

## Package Structure Benefits

✅ **Maintainability**: Easy to locate and modify code
✅ **Testability**: Pure business logic dapat di-test tanpa Android dependencies
✅ **Scalability**: Easy to add new features tanpa mengubah existing code
✅ **Team Collaboration**: Clear boundaries memudahkan parallel development
✅ **Code Reusability**: Domain layer dapat digunakan di platform lain

## Migration Path (Future)

Jika perlu migrate ke:
- **Multi-platform**: Domain layer dapat direuse
- **Different UI framework**: Hanya replace presentation layer
- **Different database**: Hanya replace data layer
- **Cloud backend**: Add remote data source di data layer

---

**Architecture Philosophy**:
"Make the code easy to understand, easy to test, and easy to change"
