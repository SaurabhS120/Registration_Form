package com.example.registrationform.location.room

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.UserDetailsData

@Dao
interface LocationDao {

    @Insert
    fun insertLocation(location: LocationDBRec)
}