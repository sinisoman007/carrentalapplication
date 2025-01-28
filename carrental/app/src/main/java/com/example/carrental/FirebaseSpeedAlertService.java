package com.example.carrental;

/**
 * @Author: Sini
 * @Date: 23/01/25
 * @Module : com.example.carrental
 * @functionality :
 */
public class FirebaseSpeedAlertService implements SpeedAlertService{
    @Override
    public void notifyRentalCompany(String carId, String renterId, int currentSpeed) {
       // sendNotificationToFirebase();
    }

    @Override
    public void notifyRenter(String renterId, String alertMessage) {
        // Send alert to user (Need to create UI for displaying alert)
        // DisplayAlert();
    }
}
