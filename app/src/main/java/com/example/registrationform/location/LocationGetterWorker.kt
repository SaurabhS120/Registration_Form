package com.example.registrationform.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.*
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class LocationGetterWorker(val appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    companion object{
        const val KEY = "location Getter worker"
    }
    private var fusedLocationClient: FusedLocationProviderClient
    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
    }
    suspend fun getCurrentLocation(): Location? {
        return withContext(Dispatchers.IO) {
            lateinit var loc:Deferred<Location?>

            loc.await()
        }
    }
    override suspend fun doWork(): Result {
        val resultChannel = Channel<Result>(1)
        withContext(Dispatchers.IO) {
            launch {
                if (ActivityCompat.checkSelfPermission(
                        appContext,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        appContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                        launch {
                            val data = workDataOf(WorkerConstants.KEY_RESULT to location)
                            val result = Result.success(data)
                            resultChannel.send(result)
                        }
                    }
                    fusedLocationClient.lastLocation.addOnFailureListener {
                        launch{
                            val data = workDataOf(WorkerConstants.KEY_ERROR to it)
                            val result = Result.failure(data)
                            resultChannel.send(result)
                        }
                    }
                }
            }
        }
        return withContext(Dispatchers.IO){
            return@withContext resultChannel.receive()
        }

    }
}