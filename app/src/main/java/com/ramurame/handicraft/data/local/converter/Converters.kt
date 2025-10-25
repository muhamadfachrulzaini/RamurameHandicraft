package com.ramurame.handicraft.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramurame.handicraft.domain.model.OrderItem
import com.ramurame.handicraft.domain.model.StatusHistory

/**
 * Type converters untuk Room Database
 */
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItem> {
        val listType = object : TypeToken<List<OrderItem>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStatusHistoryList(value: List<StatusHistory>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStatusHistoryList(value: String): List<StatusHistory> {
        val listType = object : TypeToken<List<StatusHistory>>() {}.type
        return gson.fromJson(value, listType)
    }
}
