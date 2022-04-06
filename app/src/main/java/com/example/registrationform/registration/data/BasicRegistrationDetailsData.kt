package com.example.registrationform.registration.data

import androidx.room.Entity

@Entity(tableName = "basic_details")
data class BasicRegistrationDetailsData(
    var firstName : String,
    var lastName : String,
    var phoneNo : String,
    var email : String,
    var gender : String,
    var password : String
)
