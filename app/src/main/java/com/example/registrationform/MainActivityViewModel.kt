package com.example.registrationform

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationform.registration.AddressDetailsFragment
import com.example.registrationform.registration.BasicDetailsRegistrationFragment
import com.example.registrationform.registration.EducationDetailsFragment
import com.example.registrationform.registration.data.AddressDetailsData
import com.example.registrationform.registration.data.BasicRegistrationDetailsData
import com.example.registrationform.registration.data.EducationDetailsData
import com.example.registrationform.registration.data.UserDetailsData
import com.example.registrationform.room.UsersDao
import com.example.registrationform.room.UsersDatabase
import com.example.registrationform.room.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val usersDao: UsersDao): ViewModel() {
    //data objects to save into room database
    private lateinit var basicRegistrationDetailsData:BasicRegistrationDetailsData
    private lateinit var educationDetailsData:EducationDetailsData
    private lateinit var addressDetailsData: AddressDetailsData
    private val fragments = listOf(
        //all registration fragments to get user details
        BasicDetailsRegistrationFragment(),
        EducationDetailsFragment(),
        AddressDetailsFragment()
    )
    //next previous and submit button of these activities will be handled by this view model
    init {
        fragments.first().hasPreviousFragment=false
        fragments.last().hasNextFragment=false

        fragments.forEach {
            it.setViewModel(this)
            it.textForNextButton = textForNextButton(it.hasNextFragment)
        }
    }
    //currently visible fragment
    private var currentPosition=0
    var currentFragment = MutableLiveData(fragments[currentPosition])

    fun nextFragment(){
        if (hasNextFragment()){
            currentFragment.postValue(fragments[++currentPosition])
        }else{
            viewModelScope.launch(Dispatchers.IO){
                submit()
            }
        }
    }
    fun previousFragment(){
        if (hasPreviousFragment()){
            currentFragment.postValue(fragments[--currentPosition])
        }
    }
    private fun hasPreviousFragment():Boolean{
        return currentPosition>0
    }
    private fun hasNextFragment(): Boolean {
        return currentPosition<fragments.size-1
    }
    private fun textForNextButton(hasNext:Boolean):String{
        return if (hasNext) "Next"
        else "Submit"
    }
    private fun submit(){
        val userDetailsData = UserDetailsData(0,basicRegistrationDetailsData,educationDetailsData,addressDetailsData)
        viewModelScope.launch (Dispatchers.IO){
            usersDao.insertUser(userDetailsData)
        }
    }
    fun <T> setData(obj:T){
        if (obj is BasicRegistrationDetailsData){
            basicRegistrationDetailsData = obj.copy()
        }
        else if (obj is EducationDetailsData){
            educationDetailsData = obj.copy()
        }
        else if (obj is AddressDetailsData){
            addressDetailsData = obj.copy()
        }
        else {
            throw Exception("data class is not recognized by view model")
        }
    }
}