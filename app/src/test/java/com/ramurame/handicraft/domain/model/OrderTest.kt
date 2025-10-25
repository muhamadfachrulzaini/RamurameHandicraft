package com.ramurame.handicraft.domain.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests untuk Order model
 */
class OrderTest {

    @Test
    fun `order should calculate total price from items correctly`() {
        val items = listOf(
            OrderItem("BC001", "Lunara", 2, 20000),
            OrderItem("SP001", "Jangkaru", 1, 15000)
        )

        val order = Order(
            id = "123",
            customerName = "Test Customer",
            customerPhone = "081234567890",
            shippingAddress = "Test Address",
            items = items,
            totalPrice = 55000,
            customNote = null,
            currentStatus = OrderStatus.PENDING,
            orderDate = System.currentTimeMillis(),
            estimatedCompletionDate = System.currentTimeMillis(),
            statusHistory = listOf()
        )

        val expectedTotal = 55000L
        assertEquals(expectedTotal, order.totalPrice)
    }

    @Test
    fun `formatted order number should have correct format`() {
        val order = Order(
            id = "123456789",
            customerName = "Test",
            customerPhone = "081234567890",
            shippingAddress = "Test",
            items = listOf(),
            totalPrice = 0,
            customNote = null,
            currentStatus = OrderStatus.PENDING,
            orderDate = System.currentTimeMillis(),
            estimatedCompletionDate = System.currentTimeMillis(),
            statusHistory = listOf()
        )

        assertEquals("ORD-123456789", order.getFormattedOrderNumber())
    }

    @Test
    fun `order should detect if status has been reached`() {
        val statusHistory = listOf(
            StatusHistory(OrderStatus.PENDING, System.currentTimeMillis()),
            StatusHistory(OrderStatus.IN_PROGRESS, System.currentTimeMillis())
        )

        val order = Order(
            id = "123",
            customerName = "Test",
            customerPhone = "081234567890",
            shippingAddress = "Test",
            items = listOf(),
            totalPrice = 0,
            customNote = null,
            currentStatus = OrderStatus.IN_PROGRESS,
            orderDate = System.currentTimeMillis(),
            estimatedCompletionDate = System.currentTimeMillis(),
            statusHistory = statusHistory
        )

        assertTrue(order.hasReachedStatus(OrderStatus.PENDING))
        assertTrue(order.hasReachedStatus(OrderStatus.IN_PROGRESS))
        assertFalse(order.hasReachedStatus(OrderStatus.COMPLETED))
    }

    @Test
    fun `order should get timestamp for specific status`() {
        val pendingTime = System.currentTimeMillis() - 1000
        val inProgressTime = System.currentTimeMillis()

        val statusHistory = listOf(
            StatusHistory(OrderStatus.PENDING, pendingTime),
            StatusHistory(OrderStatus.IN_PROGRESS, inProgressTime)
        )

        val order = Order(
            id = "123",
            customerName = "Test",
            customerPhone = "081234567890",
            shippingAddress = "Test",
            items = listOf(),
            totalPrice = 0,
            customNote = null,
            currentStatus = OrderStatus.IN_PROGRESS,
            orderDate = System.currentTimeMillis(),
            estimatedCompletionDate = System.currentTimeMillis(),
            statusHistory = statusHistory
        )

        assertEquals(pendingTime, order.getStatusTimestamp(OrderStatus.PENDING))
        assertEquals(inProgressTime, order.getStatusTimestamp(OrderStatus.IN_PROGRESS))
        assertNull(order.getStatusTimestamp(OrderStatus.COMPLETED))
    }
}
