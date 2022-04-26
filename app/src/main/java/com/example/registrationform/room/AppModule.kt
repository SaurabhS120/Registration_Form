package com.example.registrationform.room

import android.content.Context
import android.location.Location
import com.example.registrationform.location.room.LocationDao
import com.example.registrationform.location.room.LocationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getUsersDao(@ApplicationContext context: Context):UsersDao{
        return UsersDatabase.getDatabase(context).usersDao()
    }
    @Provides
    @Singleton
    fun getLocationDao(@ApplicationContext context: Context): LocationDao {
        return LocationDatabase.getDatabase(context).locationDao()
    }
}