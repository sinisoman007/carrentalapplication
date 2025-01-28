package com.example.carrental

import android.os.Parcelable

/**
 * @Author: Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */
data class Driver(val id: String, val name: String) : Parcelable {
    companion object {
        @JvmField
        val CREATOR = android.os.Parcelable.Creator<Driver> { Driver(it.readString()!!, it.readString()!!) }
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
    }

    override fun describeContents(): Int = 0
}