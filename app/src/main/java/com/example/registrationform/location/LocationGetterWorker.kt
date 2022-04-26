package com.example.registrationform.location

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.work.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class LocationGetterWorker(val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    companion object {
        const val KEY = "location Getter worker"
    }

    private var fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
    }

    override suspend fun doWork(): Result {
        return runBlocking(Dispatchers.IO) {
            val resultChannel = Channel<Result>()
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
                val locationString = Location.convert(location.getLatitude(), Location.FORMAT_DEGREES)
                        .toString() + " " + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES)
                val data = workDataOf(WorkerConstants.KEY_RESULT to locationString)
                val result = Result.success(data)
                launch(Dispatchers.IO) {
                    resultChannel.send(result)
                }
            }
            fusedLocationClient.lastLocation.addOnFailureListener {
                val data = workDataOf(WorkerConstants.KEY_ERROR to it.toString())
                Log.d("location error",it.toString())
                val result = Result.failure(data)
                launch(Dispatchers.IO){
                    resultChannel.send(result)
                }
            }
            resultChannel.receive()
        }

    }
}