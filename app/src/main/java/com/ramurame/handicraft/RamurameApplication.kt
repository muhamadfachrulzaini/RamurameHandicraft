package com.ramurame.handicraft

import android.app.Application
import com.ramurame.handicraft.data.local.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Application class untuk setup Hilt dan initialize database
 */
@HiltAndroidApp
class RamurameApplication : Application() {

    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()

        // Initialize database dengan dummy data
        applicationScope.launch {
            databaseInitializer.initializeIfNeeded()
        }
    }
}
