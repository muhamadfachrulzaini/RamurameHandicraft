package com.ramurame.handicraft.domain.usecase

import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case untuk mendapatkan semua orders
 */
class GetAllOrdersUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    operator fun invoke(): Flow<List<Order>> {
        return repository.getAllOrders()
    }
}
