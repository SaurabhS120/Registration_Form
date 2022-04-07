package com.example.registrationform.registration.data

import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address_details")
data class AddressDetailsData (
    @PrimaryKey(autoGenerate = true)
    var addDetailsId:Int = 0,
    var address: String,
    var landmark: String,
    var city: String,
    var state: String,
    var pinCode: String
)