package com.example.registrationform.registration.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
data class UserDetailsData(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    @Embedded
    val basicRegistrationDetailsData: BasicRegistrationDetailsData,
    @Embedded
    val educationDetailsData: EducationDetailsData,
    @Embedded
    val addressDetailsData: AddressDetailsData
)