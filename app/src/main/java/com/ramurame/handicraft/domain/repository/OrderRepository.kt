package com.ramurame.handicraft.domain.repository

import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface untuk Order
 * Interface ini mendefinisikan kontrak untuk akses data order
 */
interface OrderRepository {

    /**
     * Get all orders as Flow untuk reactive updates
     */
    fun getAllOrders(): Flow<List<Order>>

    /**
     * Get orders filtered by status
     */
    fun getOrdersByStatus(status: OrderStatus): Flow<List<Order>>

    /**
     * Get single order by ID
     */
    suspend fun getOrderById(orderId: String): Order?

    /**
     * Get orders for today
     */
    fun getTodayOrders(): Flow<List<Order>>

    /**
     * Insert new order
     */
    suspend fun insertOrder(order: Order)

    /**
     * Update existing order
     */
    suspend fun updateOrder(order: Order)

    /**
     * Delete order
     */
    suspend fun deleteOrder(order: Order)

    /**
     * Update order status
     */
    suspend fun updateOrderStatus(orderId: String, newStatus: OrderStatus)

    /**
     * Get total orders count for today
     */
    suspend fun getTodayOrdersCount(): Int

    /**
     * Get total revenue for today
     */
    suspend fun getTodayRevenue(): Long
}
