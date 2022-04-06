package com.example.registrationform.registration.data

import androidx.lifecycle.MutableLiveData

data class AddressDetailsData (
    var address: String,
    var landmark: String,
    var city: String,
    var state: String,
    var pinCode: String
)