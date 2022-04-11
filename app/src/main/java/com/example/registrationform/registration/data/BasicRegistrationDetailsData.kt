package com.example.registrationform.registration.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basic_details")
data class BasicRegistrationDetailsData(
    @PrimaryKey(autoGenerate = true)
    var basicDetailId:Int = 0,
    var profilePhoto : String,
    var firstName : String,
    var lastName : String,
    var phoneNo : String,
    var email : String,
    var gender : String,
    var password : String
):Parcelable{
    constructor(`in`:Parcel):this(
        `in`.readInt(),
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
    )
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(`in`: Parcel, flags: Int) {
        `in`.writeInt(basicDetailId)
        `in`.writeString(profilePhoto)
        `in`.writeString(firstName)
        `in`.writeString(lastName)
        `in`.writeString(phoneNo)
        `in`.writeString(email)
        `in`.writeString(gender)
        `in`.writeString(password)
    }
    companion object CREATOR : Parcelable.Creator<BasicRegistrationDetailsData>{
        override fun createFromParcel(`in`: Parcel): BasicRegistrationDetailsData {
            return BasicRegistrationDetailsData(`in`)
        }

        override fun newArray(size: Int): Array<BasicRegistrationDetailsData? >{
            return arrayOfNulls(size)
        }

    }
}
