package com.example.registrationform.location

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.registrationform.location.room.LocationDBRec
import com.example.registrationform.location.room.LocationDatabase

class LocationSaveWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    val location_database = LocationDatabase.getDatabase(context).locationDao()
    override fun doWork(): Result {
        val locationString = inputData.getString(WorkerConstants.KEY_RESULT)?:""
        location_database.insertLocation(LocationDBRec(0,locationString))
        return Result.success()
    }
}