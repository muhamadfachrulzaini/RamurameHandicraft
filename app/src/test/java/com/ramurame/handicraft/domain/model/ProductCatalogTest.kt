package com.ramurame.handicraft.domain.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests untuk ProductCatalog
 */
class ProductCatalogTest {

    @Test
    fun `product catalog should contain all products`() {
        val products = ProductCatalog.products

        // Total: 5 Bagcharm + 1 Bagtag + 4 Strap Phone + 1 Strap Mask = 11
        assertEquals(11, products.size)
    }

    @Test
    fun `should find product by id`() {
        val product = ProductCatalog.findById("BC001")

        assertNotNull(product)
        assertEquals("Lunara", product?.name)
        assertEquals(20000L, product?.price)
    }

    @Test
    fun `should return null for non-existent id`() {
        val product = ProductCatalog.findById("INVALID")

        assertNull(product)
    }

    @Test
    fun `should find product by name`() {
        val product = ProductCatalog.findByName("Choco")

        assertNotNull(product)
        assertEquals("BC003", product?.id)
        assertEquals(35000L, product?.price)
    }

    @Test
    fun `should return null for non-existent name`() {
        val product = ProductCatalog.findByName("Invalid Product")

        assertNull(product)
    }

    @Test
    fun `all bagcharm products should have correct category`() {
        val bagcharms = ProductCatalog.products.filter {
            it.category == ProductCategory.BAGCHARM
        }

        assertEquals(5, bagcharms.size)
        assertTrue(bagcharms.all { it.id.startsWith("BC") })
    }

    @Test
    fun `product prices should be positive`() {
        ProductCatalog.products.forEach { product ->
            assertTrue("Product ${product.name} has invalid price", product.price > 0)
        }
    }
}
