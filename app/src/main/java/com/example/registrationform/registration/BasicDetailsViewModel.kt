package com.example.registrationform.registration

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicDetailsViewModel: ViewModel() {
    @Bindable
    val firstName = MutableLiveData("")
    @Bindable
    val lastName = MutableLiveData("")
    @Bindable
    val phoneNo = MutableLiveData("")
    @Bindable
    val email = MutableLiveData("")
    @Bindable
    val gender = MutableLiveData("")
    @Bindable
    val isMale = MutableLiveData(false)
    @Bindable
    val isFemale = MutableLiveData(false)
    @Bindable
    val password = MutableLiveData("")
    @Bindable
    val confirmPassword = MutableLiveData("")
}