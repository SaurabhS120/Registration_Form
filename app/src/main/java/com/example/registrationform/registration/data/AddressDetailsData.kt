package com.example.registrationform.registration.data

import androidx.lifecycle.MutableLiveData
import androidx.room.Entity

@Entity(tableName = "address_details")
data class AddressDetailsData (
    var address: String,
    var landmark: String,
    var city: String,
    var state: String,
    var pinCode: String
)