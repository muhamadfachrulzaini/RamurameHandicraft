package com.ramurame.handicraft.domain.usecase

import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case untuk mendapatkan orders berdasarkan status
 */
class GetOrdersByStatusUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(status: OrderStatus): Flow<List<Order>> {
        return repository.getOrdersByStatus(status)
    }
}
