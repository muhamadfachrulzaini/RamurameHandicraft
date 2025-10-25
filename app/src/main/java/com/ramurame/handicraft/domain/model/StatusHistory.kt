package com.ramurame.handicraft.domain.model

/**
 * Model untuk riwayat perubahan status
 */
data class StatusHistory(
    val status: OrderStatus,
    val timestamp: Long,
    val note: String? = null
)
