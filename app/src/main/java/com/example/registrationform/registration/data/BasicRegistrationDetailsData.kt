package com.example.registrationform.registration.data

data class BasicRegistrationDetailsData(
    var firstName : String = "",
    var lastName : String = "",
    var phoneNo : String = "",
    var email : String = "",
    var gender : String = "",
    var password : String = "",
    var confirmPassword : String = ""
)
