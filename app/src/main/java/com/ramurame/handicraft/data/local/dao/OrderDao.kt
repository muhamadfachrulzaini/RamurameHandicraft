package com.ramurame.handicraft.data.local.dao

import androidx.room.*
import com.ramurame.handicraft.data.local.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object untuk Order
 */
@Dao
interface OrderDao {

    @Query("SELECT * FROM orders ORDER BY orderDate DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders WHERE currentStatus = :status ORDER BY orderDate DESC")
    fun getOrdersByStatus(status: String): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderById(orderId: String): OrderEntity?

    @Query("SELECT * FROM orders WHERE orderDate >= :startOfDay AND orderDate < :endOfDay ORDER BY orderDate DESC")
    fun getOrdersByDateRange(startOfDay: Long, endOfDay: Long): Flow<List<OrderEntity>>

    @Query("SELECT COUNT(*) FROM orders WHERE orderDate >= :startOfDay AND orderDate < :endOfDay")
    suspend fun getOrdersCountByDateRange(startOfDay: Long, endOfDay: Long): Int

    @Query("SELECT SUM(totalPrice) FROM orders WHERE orderDate >= :startOfDay AND orderDate < :endOfDay")
    suspend fun getTotalRevenueByDateRange(startOfDay: Long, endOfDay: Long): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<OrderEntity>)

    @Update
    suspend fun updateOrder(order: OrderEntity)

    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()
}
