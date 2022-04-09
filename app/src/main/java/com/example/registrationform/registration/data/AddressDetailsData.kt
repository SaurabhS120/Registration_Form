package com.example.registrationform.registration.data

import android.os.Parcel
import android.os.Parcelable
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
):Parcelable{
    constructor(`in`:Parcel):this(
        `in`.readInt(),
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:"",
        `in`.readString()?:""
    )
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel?, flags: Int) {
        out?.writeInt(addDetailsId)
        out?.writeString(address)
        out?.writeString(landmark)
        out?.writeString(city)
        out?.writeString(state)
        out?.writeString(pinCode)
    }
    companion object CREATOR:Parcelable.Creator<AddressDetailsData>{
        override fun createFromParcel(`in`: Parcel): AddressDetailsData {
            return AddressDetailsData(`in`)
        }

        override fun newArray(size: Int): Array<AddressDetailsData?> {
            return arrayOfNulls(size)
        }
    }

}