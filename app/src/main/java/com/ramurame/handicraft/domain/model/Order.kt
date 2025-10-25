package com.ramurame.handicraft.domain.model

/**
 * Model untuk Order (Domain Layer)
 * Model ini adalah representasi bisnis logic yang digunakan di seluruh aplikasi
 */
data class Order(
    val id: String,
    val customerName: String,
    val customerPhone: String,
    val shippingAddress: String,
    val items: List<OrderItem>,
    val totalPrice: Long,
    val customNote: String? = null,
    val currentStatus: OrderStatus,
    val orderDate: Long,
    val estimatedCompletionDate: Long,
    val statusHistory: List<StatusHistory>
) {
    /**
     * Mendapatkan timestamp dari status tertentu
     */
    fun getStatusTimestamp(status: OrderStatus): Long? {
        return statusHistory.find { it.status == status }?.timestamp
    }

    /**
     * Mengecek apakah order sudah mencapai status tertentu
     */
    fun hasReachedStatus(status: OrderStatus): Boolean {
        return statusHistory.any { it.status == status }
    }

    /**
     * Generate formatted order number untuk display
     */
    fun getFormattedOrderNumber(): String {
        return "ORD-${id}"
    }

    companion object {
        /**
         * Generate ID unik untuk order baru
         */
        fun generateOrderId(): String {
            return System.currentTimeMillis().toString()
        }
    }
}
