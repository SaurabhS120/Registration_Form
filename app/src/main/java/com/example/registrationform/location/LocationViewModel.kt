package com.example.registrationform.location

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class LocationViewModel:ViewModel() {
    fun startLocationWorker(context: Context){
        val locationWorker = PeriodicWorkRequestBuilder<LocationWorker>(15,TimeUnit.MINUTES).build()
        val continuation = WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(LocationWorker.KEY,ExistingPeriodicWorkPolicy.REPLACE,locationWorker)
    }
}