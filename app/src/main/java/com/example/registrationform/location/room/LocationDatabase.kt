package com.example.registrationform.location.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.registrationform.registration.data.AddressDetailsData
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.EducationDetailsData
import com.example.registrationform.registration.data.UserDetailsData

@Database(entities = [UserDetailsData::class,BasicRegistrationDetailsData::class,EducationDetailsData::class,AddressDetailsData::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao():LocationDao
    companion object{
        @Volatile
        private var INSTANCE: LocationDatabase? = null
        fun getDatabase(context: Context):LocationDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                LocationDatabase::class.java,
                "location_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}