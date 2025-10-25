package com.ramurame.handicraft.data.local.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramurame.handicraft.data.local.entity.OrderEntity
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderItem
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.model.StatusHistory

/**
 * Mapper untuk konversi antara Entity (Data Layer) dan Model (Domain Layer)
 */
object OrderMapper {
    private val gson = Gson()

    /**
     * Convert Entity to Domain Model
     */
    fun OrderEntity.toDomain(): Order {
        val itemsListType = object : TypeToken<List<OrderItem>>() {}.type
        val itemsList: List<OrderItem> = gson.fromJson(items, itemsListType)

        val historyListType = object : TypeToken<List<StatusHistory>>() {}.type
        val historyList: List<StatusHistory> = gson.fromJson(statusHistory, historyListType)

        return Order(
            id = id,
            customerName = customerName,
            customerPhone = customerPhone,
            shippingAddress = shippingAddress,
            items = itemsList,
            totalPrice = totalPrice,
            customNote = customNote,
            currentStatus = OrderStatus.fromString(currentStatus),
            orderDate = orderDate,
            estimatedCompletionDate = estimatedCompletionDate,
            statusHistory = historyList
        )
    }

    /**
     * Convert Domain Model to Entity
     */
    fun Order.toEntity(): OrderEntity {
        return OrderEntity(
            id = id,
            customerName = customerName,
            customerPhone = customerPhone,
            shippingAddress = shippingAddress,
            items = gson.toJson(items),
            totalPrice = totalPrice,
            customNote = customNote,
            currentStatus = currentStatus.name,
            orderDate = orderDate,
            estimatedCompletionDate = estimatedCompletionDate,
            statusHistory = gson.toJson(statusHistory)
        )
    }
}
