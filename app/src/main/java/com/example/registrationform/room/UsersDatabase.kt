package com.example.registrationform.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.registrationform.registration.data.AddressDetailsData
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.EducationDetailsData
import com.example.registrationform.registration.data.UserDetailsData

@Database(entities = [UserDetailsData::class,BasicRegistrationDetailsData::class,EducationDetailsData::class,AddressDetailsData::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao():UsersDao
    companion object{
        @Volatile
        private var INSTANCE: UsersDatabase? = null
        fun getDatabase(context: Context):UsersDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                UsersDatabase::class.java,
                "users_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}