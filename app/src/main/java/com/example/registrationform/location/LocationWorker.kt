package com.example.registrationform.location

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class LocationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    companion object{
        const val KEY = "location worker"
    }
    override fun doWork(): Result {
        val locationWorker = OneTimeWorkRequestBuilder<LocationWorker>().build()
        val locationToStringWorker = OneTimeWorkRequestBuilder<LocationToStringWorker>().build()
        val saveWorker = OneTimeWorkRequestBuilder<LocationSaveWorker>().build()
        val continuation = WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(LocationGetterWorker.KEY, ExistingWorkPolicy.REPLACE, locationWorker)
            .then(locationToStringWorker)
            .then(saveWorker)
            .enqueue()
        return Result.success()
    }
}