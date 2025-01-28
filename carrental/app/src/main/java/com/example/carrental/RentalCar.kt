package com.example.carrental

import android.os.Parcelable

/**
 * @Author Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */
data class RentalCar(val id: String, val licensePlate: String, val maxAllowedSpeed: Int) :
    Parcelable {
    companion object {
        @JvmField val CREATOR = android.os.Parcelable.Creator<RentalCar> { RentalCar(it.readString()!!, it.readString()!!, it.readInt()) }
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(licensePlate)
        dest.writeInt(maxAllowedSpeed)
    }

    override fun describeContents(): Int = 0
}