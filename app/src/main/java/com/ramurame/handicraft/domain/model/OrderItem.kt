package com.ramurame.handicraft.domain.model

/**
 * Model untuk item dalam order
 */
data class OrderItem(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val pricePerUnit: Long,
    val customNote: String? = null
) {
    val subtotal: Long
        get() = pricePerUnit * quantity
}
