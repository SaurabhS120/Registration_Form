package com.example.registrationform.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.registrationform.registration.data.AddressDetailsData
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.EducationDetailsData
import com.example.registrationform.registration.data.UserDetailsData

@Database(entities = [
    UserDetailsData::class,BasicRegistrationDetailsData::class,
    EducationDetailsData::class,
    AddressDetailsData::class,
                     ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,AppDatabase::class.java, "contact_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}