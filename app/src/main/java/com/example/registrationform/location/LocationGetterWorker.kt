package com.example.registrationform.location

import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class LocationGetterWorker(val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    companion object {
        const val KEY = "location Getter worker"
    }

    private var fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return runBlocking(Dispatchers.IO) {
            val resultChannel = Channel<Result>()
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
                val locationString = Location.convert(location.getLatitude(), Location.FORMAT_DEGREES)
                        .toString() + " " + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES)
                val date = SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Date())
                Log.d("location",locationString)
                Log.d("date",date)
                val data = workDataOf(
                    WorkerConstants.KEY_RESULT to locationString,
                    WorkerConstants.KEY_DATE to date

                )
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