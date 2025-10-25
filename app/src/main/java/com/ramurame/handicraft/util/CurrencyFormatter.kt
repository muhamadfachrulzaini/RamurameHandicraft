package com.ramurame.handicraft.util

import java.text.NumberFormat
import java.util.Locale

/**
 * Utility untuk format currency
 */
object CurrencyFormatter {

    private val indonesianLocale = Locale("id", "ID")

    /**
     * Format Long menjadi format Rupiah
     */
    fun formatToRupiah(amount: Long): String {
        val formatter = NumberFormat.getCurrencyInstance(indonesianLocale)
        return formatter.format(amount)
    }

    /**
     * Format tanpa simbol Rp
     */
    fun formatToRupiahWithoutSymbol(amount: Long): String {
        val formatter = NumberFormat.getNumberInstance(indonesianLocale)
        return formatter.format(amount)
    }
}
