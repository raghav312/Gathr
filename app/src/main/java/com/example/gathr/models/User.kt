package com.example.gathr.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

//data class for firebase db models
//store user with these as columns
data class User(val uid: String? = "", val username: String? ="") : Serializable{
}
