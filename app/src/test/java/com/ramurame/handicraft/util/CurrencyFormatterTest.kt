package com.ramurame.handicraft.util

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests untuk CurrencyFormatter
 */
class CurrencyFormatterTest {

    @Test
    fun `should format rupiah with symbol`() {
        val formatted = CurrencyFormatter.formatToRupiah(50000)

        assertTrue(formatted.contains("50"))
        assertTrue(formatted.contains("000"))
    }

    @Test
    fun `should format rupiah without symbol`() {
        val formatted = CurrencyFormatter.formatToRupiahWithoutSymbol(50000)

        assertTrue(formatted.contains("50"))
        assertFalse(formatted.contains("Rp"))
    }

    @Test
    fun `should format zero correctly`() {
        val formatted = CurrencyFormatter.formatToRupiah(0)

        assertNotNull(formatted)
    }

    @Test
    fun `should format large numbers correctly`() {
        val formatted = CurrencyFormatter.formatToRupiah(1000000)

        assertTrue(formatted.contains("1"))
        assertTrue(formatted.contains("000"))
    }
}
