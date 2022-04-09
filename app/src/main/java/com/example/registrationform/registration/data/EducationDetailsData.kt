package com.example.registrationform.registration.data

import android.os.Parcel
import android.os.Parcelable
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
):Parcelable{
    constructor(`in`:Parcel):this(
        `in`.readInt(),
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:""
    )
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(`in`: Parcel, flags: Int) {
        `in`.writeInt(eDetailId)
        `in`.writeString(education)
        `in`.writeString(year_of_passing)
        `in`.writeString(grade)
        `in`.writeString(experience)
        `in`.writeString(designation)
        `in`.writeString(domain)
    }
    companion object CREATOR : Parcelable.Creator<EducationDetailsData>{
        override fun createFromParcel(`in`: Parcel): EducationDetailsData {
            return EducationDetailsData(`in`)
        }

        override fun newArray(size: Int): Array<EducationDetailsData? > {
            return arrayOfNulls(size)
        }
    }
}