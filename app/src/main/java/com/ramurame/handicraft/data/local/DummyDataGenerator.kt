package com.ramurame.handicraft.data.local

import com.ramurame.handicraft.domain.model.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

/**
 * Generator untuk data dummy testing
 */
object DummyDataGenerator {

    /**
     * Generate list of dummy orders untuk testing
     */
    fun generateDummyOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val now = System.currentTimeMillis()
        val oneDayMillis = TimeUnit.DAYS.toMillis(1)

        // Order 1 - Pending (Hari ini)
        orders.add(
            Order(
                id = "1729843200001",
                customerName = "Siti Nurhaliza",
                customerPhone = "081234567890",
                shippingAddress = "Jl. Merdeka No. 123, Jakarta Pusat, DKI Jakarta 10110",
                items = listOf(
                    OrderItem(
                        productId = "BC001",
                        productName = "Lunara",
                        quantity = 2,
                        pricePerUnit = 20000,
                        customNote = "Warna biru dan pink"
                    ),
                    OrderItem(
                        productId = "SP001",
                        productName = "Jangkaru",
                        quantity = 1,
                        pricePerUnit = 15000,
                        customNote = null
                    )
                ),
                totalPrice = 55000,
                customNote = "Tolong dikemas dengan rapih, ini untuk kado",
                currentStatus = OrderStatus.PENDING,
                orderDate = now - TimeUnit.HOURS.toMillis(2),
                estimatedCompletionDate = now + oneDayMillis * 3,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - TimeUnit.HOURS.toMillis(2)
                    )
                )
            )
        )

        // Order 2 - In Progress (Hari ini)
        orders.add(
            Order(
                id = "1729843200002",
                customerName = "Budi Santoso",
                customerPhone = "081298765432",
                shippingAddress = "Jl. Sudirman No. 45, Bandung, Jawa Barat 40123",
                items = listOf(
                    OrderItem(
                        productId = "BC002",
                        productName = "Sanggita Satine",
                        quantity = 3,
                        pricePerUnit = 25000,
                        customNote = "Tambahkan nama 'Budi', 'Ani', 'Citra'"
                    ),
                    OrderItem(
                        productId = "BT001",
                        productName = "Bagtag Nama",
                        quantity = 3,
                        pricePerUnit = 10000,
                        customNote = "Nama sama dengan bagcharm"
                    )
                ),
                totalPrice = 105000,
                customNote = "Pengiriman ekspres jika bisa",
                currentStatus = OrderStatus.IN_PROGRESS,
                orderDate = now - TimeUnit.HOURS.toMillis(5),
                estimatedCompletionDate = now + oneDayMillis * 2,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - TimeUnit.HOURS.toMillis(5)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - TimeUnit.HOURS.toMillis(3)
                    )
                )
            )
        )

        // Order 3 - Ready to Ship (Kemarin)
        orders.add(
            Order(
                id = "1729756800001",
                customerName = "Dewi Lestari",
                customerPhone = "082187654321",
                shippingAddress = "Jl. Gatot Subroto No. 88, Surabaya, Jawa Timur 60271",
                items = listOf(
                    OrderItem(
                        productId = "BC003",
                        productName = "Choco",
                        quantity = 1,
                        pricePerUnit = 35000,
                        customNote = "Warna coklat tua"
                    ),
                    OrderItem(
                        productId = "SP004",
                        productName = "Sasmaya",
                        quantity = 1,
                        pricePerUnit = 30000,
                        customNote = null
                    ),
                    OrderItem(
                        productId = "SM001",
                        productName = "Ayudya",
                        quantity = 2,
                        pricePerUnit = 15000,
                        customNote = "Satu hitam, satu putih"
                    )
                ),
                totalPrice = 95000,
                customNote = null,
                currentStatus = OrderStatus.READY_TO_SHIP,
                orderDate = now - oneDayMillis - TimeUnit.HOURS.toMillis(10),
                estimatedCompletionDate = now + oneDayMillis,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - oneDayMillis - TimeUnit.HOURS.toMillis(10)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - oneDayMillis - TimeUnit.HOURS.toMillis(8)
                    ),
                    StatusHistory(
                        status = OrderStatus.READY_TO_SHIP,
                        timestamp = now - TimeUnit.HOURS.toMillis(2)
                    )
                )
            )
        )

        // Order 4 - Shipping (2 hari lalu)
        orders.add(
            Order(
                id = "1729670400001",
                customerName = "Ahmad Fauzi",
                customerPhone = "085612345678",
                shippingAddress = "Jl. Ahmad Yani No. 77, Yogyakarta, DI Yogyakarta 55223",
                items = listOf(
                    OrderItem(
                        productId = "BC004",
                        productName = "Jalu",
                        quantity = 5,
                        pricePerUnit = 20000,
                        customNote = "Mix warna, bebas pilihkan yang bagus"
                    )
                ),
                totalPrice = 100000,
                customNote = "Pelanggan lama, biasanya bayar transfer BCA",
                currentStatus = OrderStatus.SHIPPING,
                orderDate = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(15),
                estimatedCompletionDate = now - oneDayMillis,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(15)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(12)
                    ),
                    StatusHistory(
                        status = OrderStatus.READY_TO_SHIP,
                        timestamp = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(2)
                    ),
                    StatusHistory(
                        status = OrderStatus.SHIPPING,
                        timestamp = now - oneDayMillis - TimeUnit.HOURS.toMillis(20)
                    )
                )
            )
        )

        // Order 5 - Completed (3 hari lalu)
        orders.add(
            Order(
                id = "1729584000001",
                customerName = "Linda Wijaya",
                customerPhone = "081345678901",
                shippingAddress = "Jl. Diponegoro No. 99, Semarang, Jawa Tengah 50241",
                items = listOf(
                    OrderItem(
                        productId = "BC005",
                        productName = "Yoona",
                        quantity = 2,
                        pricePerUnit = 20000,
                        customNote = "Warna pastel"
                    ),
                    OrderItem(
                        productId = "SP002",
                        productName = "Sanggita",
                        quantity = 2,
                        pricePerUnit = 15000,
                        customNote = "Matching dengan bagcharm"
                    ),
                    OrderItem(
                        productId = "BT001",
                        productName = "Bagtag Nama",
                        quantity = 2,
                        pricePerUnit = 10000,
                        customNote = "Nama: Linda & Maya"
                    )
                ),
                totalPrice = 90000,
                customNote = "Customer sangat puas, kasih bonus kecil",
                currentStatus = OrderStatus.COMPLETED,
                orderDate = now - oneDayMillis * 3 - TimeUnit.HOURS.toMillis(18),
                estimatedCompletionDate = now - oneDayMillis * 2,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - oneDayMillis * 3 - TimeUnit.HOURS.toMillis(18)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - oneDayMillis * 3 - TimeUnit.HOURS.toMillis(14)
                    ),
                    StatusHistory(
                        status = OrderStatus.READY_TO_SHIP,
                        timestamp = now - oneDayMillis * 3 - TimeUnit.HOURS.toMillis(2)
                    ),
                    StatusHistory(
                        status = OrderStatus.SHIPPING,
                        timestamp = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(12)
                    ),
                    StatusHistory(
                        status = OrderStatus.COMPLETED,
                        timestamp = now - oneDayMillis * 2 - TimeUnit.HOURS.toMillis(2)
                    )
                )
            )
        )

        // Order 6 - In Progress (Hari ini)
        orders.add(
            Order(
                id = "1729843200003",
                customerName = "Rina Puspita",
                customerPhone = "082298765432",
                shippingAddress = "Jl. Pahlawan No. 56, Malang, Jawa Timur 65112",
                items = listOf(
                    OrderItem(
                        productId = "SP003",
                        productName = "Syana",
                        quantity = 3,
                        pricePerUnit = 20000,
                        customNote = "Warna cerah"
                    )
                ),
                totalPrice = 60000,
                customNote = "Pelanggan baru, berikan packaging terbaik",
                currentStatus = OrderStatus.IN_PROGRESS,
                orderDate = now - TimeUnit.HOURS.toMillis(4),
                estimatedCompletionDate = now + oneDayMillis * 2,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - TimeUnit.HOURS.toMillis(4)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - TimeUnit.HOURS.toMillis(1)
                    )
                )
            )
        )

        // Order 7 - Pending (Hari ini)
        orders.add(
            Order(
                id = "1729843200004",
                customerName = "Hendra Kusuma",
                customerPhone = "085787654321",
                shippingAddress = "Jl. Veteran No. 34, Medan, Sumatera Utara 20111",
                items = listOf(
                    OrderItem(
                        productId = "BC001",
                        productName = "Lunara",
                        quantity = 1,
                        pricePerUnit = 20000,
                        customNote = null
                    ),
                    OrderItem(
                        productId = "BC002",
                        productName = "Sanggita Satine",
                        quantity = 1,
                        pricePerUnit = 25000,
                        customNote = null
                    ),
                    OrderItem(
                        productId = "BC003",
                        productName = "Choco",
                        quantity = 1,
                        pricePerUnit = 35000,
                        customNote = null
                    )
                ),
                totalPrice = 80000,
                customNote = "Set lengkap untuk dijual kembali",
                currentStatus = OrderStatus.PENDING,
                orderDate = now - TimeUnit.HOURS.toMillis(1),
                estimatedCompletionDate = now + oneDayMillis * 4,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - TimeUnit.HOURS.toMillis(1)
                    )
                )
            )
        )

        // Order 8 - Ready to Ship (Kemarin)
        orders.add(
            Order(
                id = "1729756800002",
                customerName = "Maya Sari",
                customerPhone = "081567891234",
                shippingAddress = "Jl. Cendrawasih No. 21, Denpasar, Bali 80114",
                items = listOf(
                    OrderItem(
                        productId = "SP001",
                        productName = "Jangkaru",
                        quantity = 4,
                        pricePerUnit = 15000,
                        customNote = "2 hitam, 2 coklat"
                    ),
                    OrderItem(
                        productId = "BT001",
                        productName = "Bagtag Nama",
                        quantity = 4,
                        pricePerUnit = 10000,
                        customNote = "Nama untuk keluarga"
                    )
                ),
                totalPrice = 100000,
                customNote = null,
                currentStatus = OrderStatus.READY_TO_SHIP,
                orderDate = now - oneDayMillis - TimeUnit.HOURS.toMillis(12),
                estimatedCompletionDate = now,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - oneDayMillis - TimeUnit.HOURS.toMillis(12)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - oneDayMillis - TimeUnit.HOURS.toMillis(8)
                    ),
                    StatusHistory(
                        status = OrderStatus.READY_TO_SHIP,
                        timestamp = now - TimeUnit.HOURS.toMillis(3)
                    )
                )
            )
        )

        // Order 9 - Completed (5 hari lalu)
        orders.add(
            Order(
                id = "1729411200001",
                customerName = "Farah Diba",
                customerPhone = "082345678912",
                shippingAddress = "Jl. Kebon Jeruk No. 12, Tangerang, Banten 15113",
                items = listOf(
                    OrderItem(
                        productId = "BC004",
                        productName = "Jalu",
                        quantity = 2,
                        pricePerUnit = 20000,
                        customNote = null
                    ),
                    OrderItem(
                        productId = "BC005",
                        productName = "Yoona",
                        quantity = 2,
                        pricePerUnit = 20000,
                        customNote = null
                    )
                ),
                totalPrice = 80000,
                customNote = "Pelanggan reseller, beri diskon 10% next order",
                currentStatus = OrderStatus.COMPLETED,
                orderDate = now - oneDayMillis * 5 - TimeUnit.HOURS.toMillis(20),
                estimatedCompletionDate = now - oneDayMillis * 3,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - oneDayMillis * 5 - TimeUnit.HOURS.toMillis(20)
                    ),
                    StatusHistory(
                        status = OrderStatus.IN_PROGRESS,
                        timestamp = now - oneDayMillis * 5 - TimeUnit.HOURS.toMillis(16)
                    ),
                    StatusHistory(
                        status = OrderStatus.READY_TO_SHIP,
                        timestamp = now - oneDayMillis * 4 - TimeUnit.HOURS.toMillis(10)
                    ),
                    StatusHistory(
                        status = OrderStatus.SHIPPING,
                        timestamp = now - oneDayMillis * 4 - TimeUnit.HOURS.toMillis(5)
                    ),
                    StatusHistory(
                        status = OrderStatus.COMPLETED,
                        timestamp = now - oneDayMillis * 3 - TimeUnit.HOURS.toMillis(8)
                    )
                )
            )
        )

        // Order 10 - Pending (Hari ini - pagi)
        orders.add(
            Order(
                id = "1729843200005",
                customerName = "Rudi Hartono",
                customerPhone = "081678912345",
                shippingAddress = "Jl. Mawar No. 88, Bogor, Jawa Barat 16111",
                items = listOf(
                    OrderItem(
                        productId = "SM001",
                        productName = "Ayudya",
                        quantity = 5,
                        pricePerUnit = 15000,
                        customNote = "Berbagai warna"
                    )
                ),
                totalPrice = 75000,
                customNote = "Order untuk kantor, perlu invoice resmi",
                currentStatus = OrderStatus.PENDING,
                orderDate = now - TimeUnit.HOURS.toMillis(6),
                estimatedCompletionDate = now + oneDayMillis * 3,
                statusHistory = listOf(
                    StatusHistory(
                        status = OrderStatus.PENDING,
                        timestamp = now - TimeUnit.HOURS.toMillis(6)
                    )
                )
            )
        )

        return orders
    }
}
