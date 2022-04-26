package com.example.registrationform

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registrationform.location.LocationViewModel
import com.example.registrationform.registration.AddressDetailsViewModel
import com.example.registrationform.registration.BasicDetailsViewModel
import com.example.registrationform.registration.EducationDetailsFragmentViewModel
import com.example.registrationform.room.AppModule
import com.example.registrationform.room.UsersDao
import javax.inject.Inject

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationActivityViewModel::class.java)){
            return RegistrationActivityViewModel(getUsersDao(context)) as T
        }
        else if (modelClass.isAssignableFrom(BasicDetailsViewModel::class.java)){
            return BasicDetailsViewModel() as T
        }
        else if (modelClass.isAssignableFrom(EducationDetailsFragmentViewModel::class.java)){
            return EducationDetailsFragmentViewModel() as T
        }
        else if (modelClass.isAssignableFrom(AddressDetailsViewModel::class.java)){
            return AddressDetailsViewModel() as T
        }
        else if (modelClass.isAssignableFrom(LocationViewModel::class.java)){
            return LocationViewModel() as T
        }
        else{
            throw Exception("Unknown ViewModel Class")
        }
    }
    companion object{
        var usersDao:UsersDao? = null
        fun getUsersDao(context: Context):UsersDao{
            usersDao?.let {
                return it
            }
            return AppModule.getUsersDao(context.applicationContext)
        }
    }
}