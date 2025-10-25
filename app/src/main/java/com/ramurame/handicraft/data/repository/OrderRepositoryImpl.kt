package com.ramurame.handicraft.data.repository

import com.ramurame.handicraft.data.local.dao.OrderDao
import com.ramurame.handicraft.data.local.mapper.OrderMapper.toDomain
import com.ramurame.handicraft.data.local.mapper.OrderMapper.toEntity
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.model.StatusHistory
import com.ramurame.handicraft.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

/**
 * Implementasi OrderRepository
 */
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getOrdersByStatus(status: OrderStatus): Flow<List<Order>> {
        return orderDao.getOrdersByStatus(status.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getOrderById(orderId: String): Order? {
        return orderDao.getOrderById(orderId)?.toDomain()
    }

    override fun getTodayOrders(): Flow<List<Order>> {
        val (startOfDay, endOfDay) = getTodayRange()
        return orderDao.getOrdersByDateRange(startOfDay, endOfDay).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order.toEntity())
    }

    override suspend fun updateOrder(order: Order) {
        orderDao.updateOrder(order.toEntity())
    }

    override suspend fun deleteOrder(order: Order) {
        orderDao.deleteOrder(order.toEntity())
    }

    override suspend fun updateOrderStatus(orderId: String, newStatus: OrderStatus) {
        val order = orderDao.getOrderById(orderId)?.toDomain() ?: return

        val updatedHistory = order.statusHistory.toMutableList()
        updatedHistory.add(
            StatusHistory(
                status = newStatus,
                timestamp = System.currentTimeMillis(),
                note = null
            )
        )

        val updatedOrder = order.copy(
            currentStatus = newStatus,
            statusHistory = updatedHistory
        )

        orderDao.updateOrder(updatedOrder.toEntity())
    }

    override suspend fun getTodayOrdersCount(): Int {
        val (startOfDay, endOfDay) = getTodayRange()
        return orderDao.getOrdersCountByDateRange(startOfDay, endOfDay)
    }

    override suspend fun getTodayRevenue(): Long {
        val (startOfDay, endOfDay) = getTodayRange()
        return orderDao.getTotalRevenueByDateRange(startOfDay, endOfDay) ?: 0L
    }

    /**
     * Helper function untuk mendapatkan range waktu hari ini
     */
    private fun getTodayRange(): Pair<Long, Long> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endOfDay = calendar.timeInMillis

        return Pair(startOfDay, endOfDay)
    }
}
