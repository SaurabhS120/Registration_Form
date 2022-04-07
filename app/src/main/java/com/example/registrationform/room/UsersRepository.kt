package com.example.registrationform.room

import com.example.registrationform.registration.data.UserDetailsData
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersDao: UsersDao) {
    fun insertUser(userDetailsData: UserDetailsData) = usersDao.insertUser(userDetailsData)
    fun getUsers()=usersDao.getUsers()
}