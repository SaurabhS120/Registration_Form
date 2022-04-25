package com.example.registrationform.location

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.*
import java.util.concurrent.TimeUnit

class LocationViewModel:ViewModel() {
    fun startLocationWorker(application: Application){
        val locationWorker = PeriodicWorkRequestBuilder<LocationWorker>(15,TimeUnit.MINUTES).build()
        val continuation = WorkManager.getInstance(application.applicationContext)
            .enqueueUniquePeriodicWork(LocationWorker.KEY,ExistingPeriodicWorkPolicy.REPLACE,locationWorker)
    }
}