package com.blumek.dymek.thermometer.services;

import androidx.annotation.NonNull;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;

import com.blumek.dymek.MainActivity;
import com.blumek.dymek.R;
import com.blumek.dymek.devices.models.Device;

import static com.blumek.dymek.BaseApplication.CHANNEL_THERMOMETER;


public class ThermometerService extends LifecycleService {
    private static final int SERVICE_ID = 1;
    private IBinder binder;
    private Device device;

    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        super.onBind(intent);
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
    public int onStartCommand(@NonNull Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    public void setDevice(Device device) {
        if (!isNewDevice(device))
            return;

        disconnectDevice();
        this.device = device;
        connectDevice();
    }

    private void disconnectDevice() {
        if (this.device != null)
            this.device.disconnect();
    }

    private void connectDevice() {
        if (this.device != null)
            this.device.connect();
    }

    private boolean isNewDevice(Device device) {
        return device != null && !device.equals(this.device);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearDevice();
    }

    private void clearDevice() {
        if (device != null) {
            this.device.disconnect();
            this.device = null;
        }
    }
}
