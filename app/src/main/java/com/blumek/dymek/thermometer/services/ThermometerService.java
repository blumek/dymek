package com.blumek.dymek.thermometer.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.blumek.dymek.MainActivity;
import com.blumek.dymek.R;

import static com.blumek.dymek.BaseApplication.CHANNEL_THERMOMETER;


public class ThermometerService extends Service {
    private static final int SERVICE_ID = 1;
    private IBinder binder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class ServiceBinder extends Binder {
        public ThermometerService getService() {
            return ThermometerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new ServiceBinder();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_THERMOMETER)
                .setContentTitle("Thermometer service")
                .setContentText("Thermometer is running.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(SERVICE_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
}
