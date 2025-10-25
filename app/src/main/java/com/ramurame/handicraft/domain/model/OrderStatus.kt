package com.ramurame.handicraft.domain.model

/**
 * Enum untuk status orderan
 */
enum class OrderStatus(val displayName: String, val emoji: String) {
    PENDING("Pending", "⭕"),
    IN_PROGRESS("Proses Pengerjaan", "🔄"),
    READY_TO_SHIP("Siap Dikirim", "✓"),
    SHIPPING("Dalam Pengiriman", "📦"),
    COMPLETED("Selesai", "✅"),
    CANCELLED("Dibatalkan", "❌");

    companion object {
        fun fromString(value: String): OrderStatus {
            return values().find { it.name == value } ?: PENDING
        }
    }
}
