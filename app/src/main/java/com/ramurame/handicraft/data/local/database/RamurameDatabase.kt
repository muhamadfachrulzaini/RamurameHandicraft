package com.ramurame.handicraft.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramurame.handicraft.data.local.converter.Converters
import com.ramurame.handicraft.data.local.dao.OrderDao
import com.ramurame.handicraft.data.local.entity.OrderEntity

/**
 * Room Database untuk Ramurame Handicraft
 */
@Database(
    entities = [OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RamurameDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        const val DATABASE_NAME = "ramurame_database"
    }
}
