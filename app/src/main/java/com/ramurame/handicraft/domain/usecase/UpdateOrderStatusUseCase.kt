package com.ramurame.handicraft.domain.usecase

import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Use case untuk update status order
 */
class UpdateOrderStatusUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String, newStatus: OrderStatus) {
        repository.updateOrderStatus(orderId, newStatus)
    }
}
