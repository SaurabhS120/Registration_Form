package com.example.registrationform.registration.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
class UserDetailsData(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @Embedded
    val basicRegistrationDetailsData: BasicRegistrationDetailsData,
    @Embedded
    val educationDetailsData: EducationDetailsData,
    @Embedded
    val addressDetailsData: AddressDetailsData
):Parcelable {
    constructor(`in`:Parcel):this(
        `in`.readInt(),
        `in`.readParcelable(BasicRegistrationDetailsData::class.java.classLoader)!!,
        `in`.readParcelable(EducationDetailsData::class.java.classLoader)!!,
        `in`.readParcelable(AddressDetailsData::class.java.classLoader)!!,
    )
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeInt(id)
        out.writeParcelable(basicRegistrationDetailsData,0)
        out.writeParcelable(educationDetailsData,0)
        out.writeParcelable(addressDetailsData,0)
    }
    companion object CREATOR:Parcelable.Creator<UserDetailsData>{
        override fun createFromParcel(`in`: Parcel): UserDetailsData {
            return UserDetailsData(`in`)
        }

        override fun newArray(size: Int): Array<UserDetailsData?> {
            return arrayOfNulls(size)
        }

    }
}