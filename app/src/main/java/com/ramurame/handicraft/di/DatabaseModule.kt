package com.ramurame.handicraft.di

import android.content.Context
import androidx.room.Room
import com.ramurame.handicraft.data.local.dao.OrderDao
import com.ramurame.handicraft.data.local.database.RamurameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module untuk Database dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRamurameDatabase(
        @ApplicationContext context: Context
    ): RamurameDatabase {
        return Room.databaseBuilder(
            context,
            RamurameDatabase::class.java,
            RamurameDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOrderDao(database: RamurameDatabase): OrderDao {
        return database.orderDao()
    }
}
