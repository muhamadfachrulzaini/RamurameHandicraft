# Changelog

All notable changes to Ramurame Handicraft App will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-10-25

### Added - Initial MVP Release

#### Core Features
- **Dashboard Screen**
  - Today's order count and revenue summary
  - Recent orders list
  - Quick navigation to main features
  - Real-time data updates

- **Orders List Screen**
  - View all orders in card layout
  - Filter by status (Pending, In Progress, Ready to Ship, Shipping, Completed, Cancelled)
  - Sort by date (newest first)
  - Search functionality
  - Display order details: customer name, phone, date, total price
  - Total orders count and revenue calculation
  - Refresh functionality

- **Order Detail Screen**
  - Complete order information display
  - QR Code generation for order tracking
  - Customer information (name, phone, address)
  - Product list with quantities and prices
  - Status timeline with timestamps
  - Custom notes display
  - Status update functionality
  - Print invoice button (UI only, functionality pending)

#### Technical Implementation
- **Architecture**
  - Clean Architecture with MVVM pattern
  - Separation into Data, Domain, and Presentation layers
  - Repository pattern for data access
  - Use Cases for business logic

- **Database**
  - Room Database for local data persistence
  - TypeConverters for complex data types
  - Flow-based reactive queries
  - Automatic dummy data population on first run

- **Dependency Injection**
  - Hilt for dependency injection
  - Modular DI setup (Database, Repository, App modules)

- **UI/UX**
  - Jetpack Compose for modern declarative UI
  - Material Design 3 components
  - Tablet landscape orientation optimization
  - Custom color scheme (terracotta/warm theme)
  - Large, readable typography for tablet viewing
  - Status-specific color coding

- **Navigation**
  - Jetpack Navigation Compose
  - Type-safe navigation arguments
  - Back stack management

#### Product Catalog
- **Bagcharm**: Lunara, Sanggita Satine, Choco, Jalu, Yoona
- **Bagtag**: Bagtag Nama
- **Strap Phone**: Jangkaru, Sanggita, Syana, Sasmaya
- **Strap Mask**: Ayudya

#### Developer Tools
- **Testing**
  - Unit tests for domain models
  - Unit tests for utility functions
  - Test coverage for business logic

- **Documentation**
  - Comprehensive README with setup instructions
  - Architecture documentation (ARCHITECTURE.md)
  - Contributing guidelines (CONTRIBUTING.md)
  - Code comments and KDoc

- **Code Quality**
  - Kotlin coding conventions
  - ProGuard rules for release builds
  - Proper error handling
  - Null safety

#### Utilities
- QR Code generation using ZXing
- Currency formatter for Rupiah
- Date formatter with Indonesian locale
- Database initializer with dummy data

### Technical Details

**Dependencies**
- Kotlin 1.9.20
- Compose BOM 2024.01.00
- Room 2.6.1
- Hilt 2.48
- Coroutines 1.7.3
- Navigation Compose 2.7.6
- ZXing Core 3.5.2
- Gson 2.10.1
- DataStore Preferences 1.0.0

**Build Configuration**
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)
- Compile SDK: 34
- JVM Target: 17
- Gradle: 8.2

**Database Schema**
- Orders table with JSON fields for complex types
- Automatic migration support
- DataStore for app preferences

### Known Limitations

- Print invoice functionality not yet implemented
- QR Code scanning not available
- Data export functionality pending
- No cloud sync (local storage only)
- No multi-user support
- No payment tracking integration

## [Unreleased]

### Planned Features
- Print invoice to PDF
- QR Code scanner for order tracking
- Export orders to Excel/CSV
- Cloud backup with Firebase
- Push notifications
- Analytics dashboard
- Multi-user access with roles
- Payment tracking
- Inventory management
- Customer database
- Report generation
- Dark mode support (full implementation)

### Under Consideration
- Barcode scanning for products
- Photo attachment for orders
- WhatsApp integration
- Email notifications
- Custom product builder
- Price calculator
- Discount/promo system
- Loyalty program

---

## Version History

- **1.0.0** (2025-10-25) - Initial MVP Release

---

## Upgrade Notes

### From Fresh Install
No migration needed. App will automatically populate with dummy data on first run.

---

## Support

For questions or issues with any version, please:
1. Check the README.md for documentation
2. Review ARCHITECTURE.md for technical details
3. Open an issue on the repository

---

**Maintained by**: Ramurame Development Team
**Last Updated**: October 25, 2025
