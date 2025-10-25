package com.ramurame.handicraft.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ramurame.handicraft.data.local.dao.OrderDao
import com.ramurame.handicraft.data.local.mapper.OrderMapper.toEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

/**
 * Initializer untuk populate database dengan data dummy
 */
@Singleton
class DatabaseInitializer @Inject constructor(
    private val orderDao: OrderDao,
    private val context: Context
) {
    companion object {
        private val IS_INITIALIZED_KEY = booleanPreferencesKey("is_database_initialized")
    }

    /**
     * Initialize database dengan dummy data jika belum pernah diinisialisasi
     */
    suspend fun initializeIfNeeded() {
        val isInitialized = context.dataStore.data
            .map { preferences ->
                preferences[IS_INITIALIZED_KEY] ?: false
            }
            .first()

        if (!isInitialized) {
            // Populate database dengan dummy data
            val dummyOrders = DummyDataGenerator.generateDummyOrders()
            val orderEntities = dummyOrders.map { it.toEntity() }
            orderDao.insertOrders(orderEntities)

            // Tandai bahwa database sudah diinisialisasi
            context.dataStore.edit { preferences ->
                preferences[IS_INITIALIZED_KEY] = true
            }
        }
    }

    /**
     * Reset database dan repopulate dengan dummy data
     * Berguna untuk development/testing
     */
    suspend fun resetDatabase() {
        orderDao.deleteAllOrders()

        val dummyOrders = DummyDataGenerator.generateDummyOrders()
        val orderEntities = dummyOrders.map { it.toEntity() }
        orderDao.insertOrders(orderEntities)

        // Update flag
        context.dataStore.edit { preferences ->
            preferences[IS_INITIALIZED_KEY] = true
        }
    }
}
