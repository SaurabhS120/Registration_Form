package com.example.registrationform.location.room

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationDBRec (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo
    val locationString: String,
    @ColumnInfo
    val date:String
    )