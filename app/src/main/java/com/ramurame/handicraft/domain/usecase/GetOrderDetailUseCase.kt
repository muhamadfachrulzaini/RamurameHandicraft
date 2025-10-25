package com.ramurame.handicraft.domain.usecase

import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Use case untuk mendapatkan detail order
 */
class GetOrderDetailUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Order? {
        return repository.getOrderById(orderId)
    }
}
