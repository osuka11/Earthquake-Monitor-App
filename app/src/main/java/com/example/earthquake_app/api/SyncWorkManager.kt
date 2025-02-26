package com.example.earthquake_app.api

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.earthquake_app.database.EqDatabase
import com.example.earthquake_app.database.getDatabase
import com.example.earthquake_app.main.MainRepository

class SyncWorkManager(appContext:Context, params:WorkerParameters):CoroutineWorker(appContext, params) {
    private val database = getDatabase(appContext)
    private val repository = MainRepository(database)

    override suspend fun doWork(): Result {
        repository.fetchEarthquakes(true)
        return Result.success()

    }

    companion object {
        const val WORK_NAME: String = "work_manager_app"
    }
}