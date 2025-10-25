package com.ramurame.handicraft.domain.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests untuk OrderItem model
 */
class OrderItemTest {

    @Test
    fun `order item should calculate subtotal correctly`() {
        val item = OrderItem(
            productId = "BC001",
            productName = "Lunara",
            quantity = 3,
            pricePerUnit = 20000
        )

        assertEquals(60000L, item.subtotal)
    }

    @Test
    fun `order item with quantity 1 should equal price per unit`() {
        val item = OrderItem(
            productId = "BC001",
            productName = "Lunara",
            quantity = 1,
            pricePerUnit = 20000
        )

        assertEquals(20000L, item.subtotal)
    }

    @Test
    fun `order item with zero quantity should have zero subtotal`() {
        val item = OrderItem(
            productId = "BC001",
            productName = "Lunara",
            quantity = 0,
            pricePerUnit = 20000
        )

        assertEquals(0L, item.subtotal)
    }
}
