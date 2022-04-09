package com.example.registrationform.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.UserDetailsData

@Dao
interface UsersDao {

    @Insert
    fun insertUser(userDetailsData: UserDetailsData)

    @Insert
    fun insertUser(basicRegistrationDetailsData: BasicRegistrationDetailsData)

    @Query("SELECT * FROM user_details")
    fun getUsers():LiveData<List<UserDetailsData>>
}