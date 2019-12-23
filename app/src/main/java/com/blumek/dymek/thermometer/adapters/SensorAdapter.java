package com.blumek.dymek.thermometer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.blumek.dymek.thermometer.fragments.SensorFragment;
import com.blumek.dymek.thermometer.models.Sensor;


public class SensorAdapter extends FragmentStateAdapter {
    private LiveData<Sensor>[] sensors;

    public SensorAdapter(@NonNull Fragment fragment) {
        super(fragment);
        //noinspection unchecked
        sensors = new LiveData[0];
    }

    public void setSensors(LiveData<Sensor>[] sensors) {
        for (LiveData<Sensor> s : sensors) {
            System.out.println(s.getValue());
        }
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new SensorFragment(sensors[position]);
    }

    @Override
    public int getItemCount() {
        return sensors == null ? 0 : sensors.length ;
    }
}
