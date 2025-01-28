package com.example.carrental

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CarRentalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_rental)

        // We can create a layout for collecting the details of rental and set the values into Rental clas
        // here just added some sample data.

        // Setup sample data
        val rentalCar = RentalCar("car1", "KL26K9943", 80)
        val driver = Driver("renter1", "SiniSOman")
        SpeedMonitorService.startAService(this, rentalCar, driver);

        // When driver returns the car we can cancel it by using stop method
        // SpeedMonitorService.stopService(this, "car1")
    }
}
