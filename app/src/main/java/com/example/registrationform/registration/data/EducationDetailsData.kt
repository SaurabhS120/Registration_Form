package com.example.registrationform.registration.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education_details")
data class EducationDetailsData(
    @PrimaryKey(autoGenerate = true)
    var eDetailId:Int = 0,
    var education:String = "",
    var year_of_passing:String = "",
    var grade:String = "",
    var experience:String = "",
    var designation:String = "",
    var domain:String = ""
)