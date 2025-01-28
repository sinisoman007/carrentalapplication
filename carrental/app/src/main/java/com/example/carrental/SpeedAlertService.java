package com.example.carrental;

/**
 * @Author: Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */
 interface SpeedAlertService {
    void notifyRentalCompany(String carId, String renterId, int currentSpeed);
    void notifyRenter(String renterId, String alertMessage);
}
