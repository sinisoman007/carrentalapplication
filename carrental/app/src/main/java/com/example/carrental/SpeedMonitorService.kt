package com.example.carrental
/**
 * @Author: Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */

import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public class SpeedMonitorService : Service(),LocationListener {

    private val carMonitors = mutableMapOf<String, SpeedMonitor>()
    private val carJobs = mutableMapOf<String, Job>()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    companion object {
        const val R_CAR = "R_CAR"
        const val R_DRIVER = "R_DRIVER"

        fun startAService(context: Context, car: RentalCar, driver: Driver) {
            val intent = Intent(context, SpeedMonitorService::class.java)
            intent.putExtra(R_CAR, car)
            intent.putExtra(R_DRIVER, driver)
            context.startService(intent)
        }

        fun stopService(context: Context, carId: String) {
            val intent = Intent(context, SpeedMonitorService::class.java)
            intent.putExtra("CAR_ID", carId)
            context.startService(intent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val car = it.getParcelableExtra<RentalCar>(R_CAR)
            val driver = it.getParcelableExtra<Driver>(R_DRIVER)

            if (car != null && driver != null) {
                monitorCar(car, driver)
            }

            val carId = it.getStringExtra("CAR_ID")
            if (carId != null) {
                stopMonitoringCar(carId)
            }
        }
        Log.d("SpeedMonitorService", "Service onStartCommand")
        return START_STICKY
    }

    private fun monitorCar(car: RentalCar, driver: Driver) {
        if (carMonitors.containsKey(car.id)) return

        val alertService = FirebaseSpeedAlertService()
        val monitor = SpeedMonitor(alertService)
        carMonitors[car.id] = monitor

        val job = scope.launch {
            val speeds = listOf(70, 85, 90) // dummy speeds
            for (speed in speeds) {
                Log.d(
                    "SpeedMonitorService",
                    "Checking speed for car: ${car.id}, Speed: $speed km/h"
                )
                monitor.checkSpeed(car, driver, speed)
                delay(5000) // Example Simulate periodic checks
            }
        }
        carJobs[car.id] = job
    }

    private fun stopMonitoringCar(carId: String) {
        carJobs[carId]?.cancel() // Cancel the coroutine for the specific car
        carJobs.remove(carId)
        carMonitors.remove(carId)
        Log.d("SpeedMonitorService", "Stopped monitoring for car: $carId")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // No binding provided
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SpeedMonitorService", "Service destroyed")
        scope.cancel() // Cancel all coroutines
    }

    override fun onLocationChanged(p0: Location) {
        // Calculates the distance traveled between the current and previous locations.
        // Calculates the speed using distance and time difference.
        // Converts speed to km/h and call speedMonitor.checkSpeed() function  for updations
    }

}