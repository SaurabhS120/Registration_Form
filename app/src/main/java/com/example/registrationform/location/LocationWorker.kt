package com.example.registrationform.location

import android.content.Context
import androidx.work.*

class LocationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    companion object{
        const val KEY = "location worker"
    }
    override fun doWork(): Result {
        val locationGetterWorker = OneTimeWorkRequestBuilder<LocationGetterWorker>().build()
        val saveWorker = OneTimeWorkRequestBuilder<LocationSaveWorker>().build()
        val continuation = WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(LocationGetterWorker.KEY, ExistingWorkPolicy.REPLACE, locationGetterWorker)
            .then(saveWorker)
            .enqueue()
        return Result.success()
    }
}