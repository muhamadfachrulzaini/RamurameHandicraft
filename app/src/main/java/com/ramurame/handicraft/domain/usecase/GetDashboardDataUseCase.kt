package com.ramurame.handicraft.domain.usecase

import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case untuk mendapatkan data dashboard
 */
class GetDashboardDataUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    fun getTodayOrders(): Flow<List<Order>> {
        return repository.getTodayOrders()
    }

    suspend fun getTodayOrdersCount(): Int {
        return repository.getTodayOrdersCount()
    }

    suspend fun getTodayRevenue(): Long {
        return repository.getTodayRevenue()
    }
}
