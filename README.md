# Ramurame Handicraft - Android MVP Application

![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Language](https://img.shields.io/badge/language-Kotlin-blue.svg)
![Architecture](https://img.shields.io/badge/architecture-MVVM%20%2B%20Clean-orange.svg)
![Database](https://img.shields.io/badge/database-Room-red.svg)

Aplikasi Android MVP untuk mengelola orderan UMKM Ramurame yang memproduksi handicraft custom dari paracord. Aplikasi ini dioptimalkan untuk penggunaan di tablet dengan orientasi landscape.

## 📱 Tentang Ramurame

Ramurame adalah UMKM yang memproduksi berbagai produk handicraft berkualitas dari paracord, meliputi:

### Produk Bagcharm
- **Lunara** - Rp 20.000
- **Sanggita Satine** - Rp 25.000
- **Choco** - Rp 35.000
- **Jalu** - Rp 20.000
- **Yoona** - Rp 20.000

### Produk Bagtag
- **Bagtag Nama** - Rp 10.000

### Produk Strap Phone
- **Jangkaru** - Rp 15.000
- **Sanggita** - Rp 15.000
- **Syana** - Rp 20.000
- **Sasmaya** - Rp 30.000

### Produk Strap Mask
- **Ayudya** - Rp 15.000

## 🎯 Fitur Utama

### 1. Dashboard
- Ringkasan order hari ini
- Total pendapatan hari ini
- Daftar order terbaru
- Navigasi cepat ke menu utama

### 2. Daftar Orderan
- List semua order dalam format card
- Filter berdasarkan status (Pending, Proses, Siap Kirim, Dalam Pengiriman, Selesai, Dibatalkan)
- Sorting otomatis berdasarkan tanggal (terbaru dulu)
- Informasi lengkap: nomor order, customer, tanggal, total harga
- Refresh untuk update data
- Total order dan total nilai

### 3. Detail Orderan
- Informasi lengkap order dengan QR Code
- Data customer (nama, no HP, alamat pengiriman)
- Daftar produk dengan quantity, harga satuan, dan subtotal
- Total harga order
- Timeline status dengan timestamp
- Catatan custom order
- Tombol untuk mengubah status order
- Tombol print invoice (prepared for future implementation)

### 4. Status Tracking
- **⭕ Pending** - Order baru, belum diproses
- **🔄 Proses Pengerjaan** - Sedang dikerjakan
- **✓ Siap Dikirim** - Selesai, menunggu pengiriman
- **📦 Dalam Pengiriman** - Sedang dalam perjalanan
- **✅ Selesai** - Diterima customer
- **❌ Dibatalkan** - Order dibatalkan

## 🏗️ Arsitektur

Aplikasi ini menggunakan **Clean Architecture** dengan **MVVM Pattern** untuk memastikan:
- Separation of Concerns
- Testability
- Maintainability
- Scalability

### Layer Structure

```
app/
├── data/                          # Data Layer
│   ├── local/
│   │   ├── dao/                  # Room DAO
│   │   ├── database/             # Room Database
│   │   ├── entity/               # Database Entities
│   │   ├── converter/            # Type Converters
│   │   └── mapper/               # Entity ↔ Model Mappers
│   └── repository/               # Repository Implementation
│
├── domain/                        # Domain Layer (Business Logic)
│   ├── model/                    # Domain Models
│   ├── repository/               # Repository Interfaces
│   └── usecase/                  # Use Cases
│
├── presentation/                  # Presentation Layer (UI)
│   ├── dashboard/                # Dashboard Screen
│   ├── orders/                   # Orders List Screen
│   ├── detail/                   # Order Detail Screen
│   ├── navigation/               # Navigation
│   └── theme/                    # UI Theme & Styling
│
├── di/                           # Dependency Injection (Hilt)
└── util/                         # Utilities
```

## 🛠️ Tech Stack

### Core
- **Kotlin** - Programming language
- **Android SDK 26+** - Minimum API level
- **Target SDK 34** - Latest Android version

### Architecture Components
- **MVVM** - Presentation pattern
- **Clean Architecture** - Software architecture
- **Jetpack Compose** - Modern UI toolkit
- **Navigation Component** - Screen navigation
- **ViewModel** - UI state management
- **LiveData & StateFlow** - Reactive data

### Database
- **Room Database** - Local persistence
- **TypeConverters** - Complex data types
- **Flow** - Reactive database queries

### Dependency Injection
- **Hilt** - DI framework

### Libraries
- **Coroutines** - Asynchronous programming
- **Gson** - JSON serialization
- **ZXing** - QR Code generation
- **DataStore** - Preferences storage
- **Material Design 3** - UI components

### Testing
- **JUnit4** - Unit testing
- **Coroutines Test** - Async testing
- **Architecture Testing** - ViewModel testing

## 🎨 Design System

### Color Palette
- **Primary**: Terracotta (#D4886D) - Warm, handmade feel
- **Secondary**: Warm Orange (#E67E50) - Accent color
- **Background**: Cream (#FFF8F0) - Soft, comfortable
- **Surface**: Light Cream (#FFFBF7) - Card background

### Typography
Optimized for tablet landscape viewing:
- **Display**: 48sp - 40sp (Headers)
- **Headline**: 32sp - 28sp (Section titles)
- **Title**: 22sp - 18sp (Card headers)
- **Body**: 16sp - 14sp (Content)
- **Label**: 16sp - 14sp (Buttons, labels)

### Status Colors
- **Pending**: Yellow (#FFC107)
- **In Progress**: Blue (#2196F3)
- **Ready to Ship**: Green (#4CAF50)
- **Shipping**: Purple (#9C27B0)
- **Completed**: Green (#43A047)
- **Cancelled**: Red (#F44336)

## 📦 Installation

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34
- Gradle 8.2+

### Setup Steps

1. **Clone repository**
```bash
git clone <repository-url>
cd RamurameHandicraft
```

2. **Open project di Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate ke folder project

3. **Sync Gradle**
   - Android Studio akan otomatis sync Gradle
   - Tunggu hingga proses selesai

4. **Build project**
```bash
./gradlew build
```

5. **Run pada emulator atau device**
   - Pilih device (recommended: Tablet 10" landscape)
   - Click Run button atau `Shift + F10`

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Test Coverage
Unit tests mencakup:
- Domain models (Order, OrderItem, Product)
- Utility functions (CurrencyFormatter, DateFormatter)
- Business logic validation

## 📊 Data Structure

### Order Model
```kotlin
data class Order(
    val id: String,                           // Unique order ID
    val customerName: String,                 // Customer name
    val customerPhone: String,                // Phone number
    val shippingAddress: String,              // Shipping address
    val items: List<OrderItem>,               // Order items
    val totalPrice: Long,                     // Total in Rupiah
    val customNote: String?,                  // Custom notes
    val currentStatus: OrderStatus,           // Current status
    val orderDate: Long,                      // Order timestamp
    val estimatedCompletionDate: Long,        // Estimated completion
    val statusHistory: List<StatusHistory>    // Status timeline
)
```

### OrderItem Model
```kotlin
data class OrderItem(
    val productId: String,       // Product ID from catalog
    val productName: String,     // Product name
    val quantity: Int,           // Quantity ordered
    val pricePerUnit: Long,      // Price per item
    val customNote: String?      // Item-specific note
)
```

## 🗃️ Database Schema

### Orders Table
```sql
CREATE TABLE orders (
    id TEXT PRIMARY KEY NOT NULL,
    customerName TEXT NOT NULL,
    customerPhone TEXT NOT NULL,
    shippingAddress TEXT NOT NULL,
    items TEXT NOT NULL,              -- JSON array
    totalPrice INTEGER NOT NULL,
    customNote TEXT,
    currentStatus TEXT NOT NULL,
    orderDate INTEGER NOT NULL,
    estimatedCompletionDate INTEGER NOT NULL,
    statusHistory TEXT NOT NULL       -- JSON array
)
```

## 🚀 Development

### Adding New Product
Edit `ProductCatalog.kt`:
```kotlin
Product("ID", "Name", ProductCategory.CATEGORY, price)
```

### Adding New Status
Edit `OrderStatus.kt`:
```kotlin
STATUS_NAME("Display Name", "Emoji")
```

### Modifying Theme
Edit files in `presentation/theme/`:
- `Color.kt` - Color definitions
- `Theme.kt` - Theme configuration
- `Type.kt` - Typography scale

## 🎯 Dummy Data

Aplikasi includes 10 dummy orders dengan berbagai status untuk testing:
- 4 orders hari ini (2 Pending, 2 In Progress)
- 2 orders kemarin (Ready to Ship)
- 2 orders 2-3 hari lalu (Shipping)
- 2 orders completed (3-5 hari lalu)

Data dummy akan otomatis dipopulate saat aplikasi pertama kali dijalankan.

## 📱 Target Device

### Recommended Specifications
- **Device Type**: Tablet
- **Screen Size**: 10 inch atau lebih besar
- **Orientation**: Landscape (fixed)
- **Resolution**: 1280x800 atau lebih tinggi
- **Android Version**: 8.0 (Oreo) atau lebih baru

### Tested On
- Android Emulator - Pixel Tablet (Landscape)
- Physical Tablet 10.1" (1920x1200)

## 🔐 Security & Privacy

- Semua data disimpan lokal di device
- Tidak ada koneksi ke server eksternal
- Data customer terenkripsi di database
- Backup otomatis via Android Backup Service

## 🐛 Known Issues & Limitations

1. **Print Invoice**: Tombol tersedia tapi implementasi pending
2. **QR Code Scanning**: Belum diimplementasi
3. **Export Data**: Fitur export belum tersedia
4. **Multi-device Sync**: Data hanya tersimpan lokal

## 🔮 Future Enhancements

- [ ] Print invoice functionality
- [ ] QR Code scanner untuk tracking
- [ ] Export data ke Excel/PDF
- [ ] Cloud sync dengan Firebase
- [ ] Push notifications untuk update status
- [ ] Analytics dashboard
- [ ] Multi-user support dengan roles
- [ ] Payment tracking integration
- [ ] Inventory management
- [ ] Customer database

## 📄 License

Copyright © 2025 Ramurame Handicraft. All rights reserved.

This is a proprietary software developed specifically for Ramurame UMKM business operations.

## 👥 Credits

**Development Team**
- Architecture & Development: Claude Code
- UI/UX Design: Material Design 3
- QR Code: ZXing Library

**Business**
- Ramurame Handicraft - UMKM Paracord Products

## 📞 Support

For issues, questions, or feature requests:
- Create an issue in the repository
- Contact: [Your contact information]

---

**Built with ❤️ for Ramurame Handicraft**

*Empowering UMKM with modern technology*
