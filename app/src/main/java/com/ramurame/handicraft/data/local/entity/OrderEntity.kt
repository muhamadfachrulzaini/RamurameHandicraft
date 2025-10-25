package com.ramurame.handicraft.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ramurame.handicraft.data.local.converter.Converters

/**
 * Entity untuk Room Database
 */
@Entity(tableName = "orders")
@TypeConverters(Converters::class)
data class OrderEntity(
    @PrimaryKey
    val id: String,
    val customerName: String,
    val customerPhone: String,
    val shippingAddress: String,
    val items: String, // JSON string dari List<OrderItem>
    val totalPrice: Long,
    val customNote: String?,
    val currentStatus: String,
    val orderDate: Long,
    val estimatedCompletionDate: Long,
    val statusHistory: String // JSON string dari List<StatusHistory>
)
