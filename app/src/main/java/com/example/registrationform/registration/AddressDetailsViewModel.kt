package com.example.registrationform.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddressDetailsViewModel :ViewModel() {
    val address = MutableLiveData("")
    val landmark = MutableLiveData("")
    val city = MutableLiveData("")
    val state = MutableLiveData("Select Your State")
    val pinCode = MutableLiveData("")
}