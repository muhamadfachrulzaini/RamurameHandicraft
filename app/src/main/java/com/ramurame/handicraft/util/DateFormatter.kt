package com.ramurame.handicraft.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Utility untuk format tanggal
 */
object DateFormatter {

    private val indonesianLocale = Locale("id", "ID")

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", indonesianLocale)
    private val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", indonesianLocale)
    private val timeFormat = SimpleDateFormat("HH:mm", indonesianLocale)

    /**
     * Format timestamp menjadi tanggal
     */
    fun formatDate(timestamp: Long): String {
        return dateFormat.format(Date(timestamp))
    }

    /**
     * Format timestamp menjadi tanggal dan waktu
     */
    fun formatDateTime(timestamp: Long): String {
        return dateTimeFormat.format(Date(timestamp))
    }

    /**
     * Format timestamp menjadi waktu saja
     */
    fun formatTime(timestamp: Long): String {
        return timeFormat.format(Date(timestamp))
    }
}
