package com.example.registrationform.registration

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicDetailsViewModel: ViewModel() {
    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val phoneNo = MutableLiveData("")
    val email = MutableLiveData("")
    val gender = MutableLiveData("")
    val isMale = MutableLiveData(false)
    val isFemale = MutableLiveData(false)
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    fun log(){
        Log.d("First name",firstName.value.toString())
        Log.d("Last name",lastName.value.toString())
        Log.d("phone no",phoneNo.value.toString())
        Log.d("email",email.value.toString())
        Log.d("Gender",gender.value.toString())
        Log.d("isMale",isMale.value.toString())
        Log.d("isFemale",isFemale.value.toString())
        Log.d("password",password.value.toString())
        Log.d("Confirm password",confirmPassword.value.toString())
    }
}