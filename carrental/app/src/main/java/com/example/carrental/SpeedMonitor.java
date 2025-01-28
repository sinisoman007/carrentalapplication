package com.example.carrental;

/**
 * @Author: Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */
class SpeedMonitor {
    private final SpeedAlertService alertService;
    //private final LocationService locationService;

    public SpeedMonitor(SpeedAlertService alertService) {
        this.alertService = alertService;
    }

    public void checkSpeed(RentalCar rentalCar, Driver driver, int currentSpeed) {
        if (currentSpeed > rentalCar.maxAllowedSpeed) {

            // Notify rental company
            alertService.notifyRentalCompany(rentalCar.id, driver.id, currentSpeed);

            // Notify renter
            alertService.notifyRenter(driver.id, "You have exceeded the maximum speed limit of " + rentalCar.maxAllowedSpeed + " km/h");
        }
    }
}

