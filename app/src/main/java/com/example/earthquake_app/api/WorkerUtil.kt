package com.example.earthquake_app.api

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkerUtil{
    fun scheduleSync(context: Context){
        val constraintSet = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val syncRequest = PeriodicWorkRequestBuilder<SyncWorkManager>(1, TimeUnit.HOURS)
            .setConstraints(constraintSet)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            SyncWorkManager.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, syncRequest
        )
    }
}