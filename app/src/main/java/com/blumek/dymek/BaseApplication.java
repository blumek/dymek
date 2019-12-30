package com.blumek.dymek;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class BaseApplication extends Application {
    public static final String CHANNEL_THERMOMETER = "DYMEK_CHANNEL_THERMOMETER";

    @Override
    public void onCreate() {
        super.onCreate();
        createThermometerChannel();
    }

    private void createThermometerChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel thermometerChannel = new NotificationChannel(CHANNEL_THERMOMETER,
                    "Thermometer channel", NotificationManager.IMPORTANCE_DEFAULT);
            thermometerChannel.setDescription("Dymek's channel for thermometer information");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(thermometerChannel);
        }
    }
}
