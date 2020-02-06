package com.blumek.dymek.thermometer.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.MainActivity;
import com.blumek.dymek.R;
import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.models.SimulationDevice;
import com.blumek.dymek.thermometer.models.Thermometer;

import static com.blumek.dymek.BaseApplication.CHANNEL_THERMOMETER;


public class ThermometerService extends LifecycleService {
    private static final int SERVICE_ID = 1;
    private IBinder binder;
    private Device device;
    private MutableLiveData<Thermometer> thermometer;

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
        thermometer = new MutableLiveData<>();
        binder = new ServiceBinder();

        device = new SimulationDevice(null, null, 3);
        connectDevice();
        setDevice();

        new Handler().postDelayed(() -> {
            device = new SimulationDevice(null, null, 2);
            connectDevice();
            setDevice();
        }, 3000);

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

    private void setDevice() {
        thermometer.setValue(device.getThermometer());
    }

    @Override
    public int onStartCommand(@NonNull Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    private void disconnectDevice() {
        if (this.device != null)
            this.device.disconnect();
    }

    private void connectDevice() {
        if (this.device != null)
            this.device.connect();
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

    public LiveData<Thermometer> getThermometer() {
        return thermometer;
    }
}
