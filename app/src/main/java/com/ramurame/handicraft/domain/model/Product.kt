package com.ramurame.handicraft.domain.model

/**
 * Model untuk produk Ramurame
 */
data class Product(
    val id: String,
    val name: String,
    val category: ProductCategory,
    val price: Long
)

enum class ProductCategory(val displayName: String) {
    BAGCHARM("Bagcharm"),
    BAGTAG("Bagtag"),
    STRAP_PHONE("Strap Phone"),
    STRAP_MASK("Strap Mask");

    companion object {
        fun fromString(value: String): ProductCategory {
            return values().find { it.name == value } ?: BAGCHARM
        }
    }
}

/**
 * Daftar produk Ramurame yang tersedia
 */
object ProductCatalog {
    val products = listOf(
        // Bagcharm
        Product("BC001", "Lunara", ProductCategory.BAGCHARM, 20000),
        Product("BC002", "Sanggita Satine", ProductCategory.BAGCHARM, 25000),
        Product("BC003", "Choco", ProductCategory.BAGCHARM, 35000),
        Product("BC004", "Jalu", ProductCategory.BAGCHARM, 20000),
        Product("BC005", "Yoona", ProductCategory.BAGCHARM, 20000),

        // Bagtag
        Product("BT001", "Bagtag Nama", ProductCategory.BAGTAG, 10000),

        // Strap Phone
        Product("SP001", "Jangkaru", ProductCategory.STRAP_PHONE, 15000),
        Product("SP002", "Sanggita", ProductCategory.STRAP_PHONE, 15000),
        Product("SP003", "Syana", ProductCategory.STRAP_PHONE, 20000),
        Product("SP004", "Sasmaya", ProductCategory.STRAP_PHONE, 30000),

        // Strap Mask
        Product("SM001", "Ayudya", ProductCategory.STRAP_MASK, 15000)
    )

    fun findById(id: String): Product? = products.find { it.id == id }
    fun findByName(name: String): Product? = products.find { it.name == name }
}
