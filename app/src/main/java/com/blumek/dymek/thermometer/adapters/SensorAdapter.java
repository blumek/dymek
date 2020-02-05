package com.blumek.dymek.thermometer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.blumek.dymek.thermometer.adapters.diffUtils.SensorLiveDataDiffCallback;
import com.blumek.dymek.thermometer.fragments.SensorFragment;
import com.blumek.dymek.thermometer.models.Sensor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SensorAdapter extends FragmentStateAdapter {
    private List<LiveData<Sensor>> sensors;

    public SensorAdapter(@NonNull Fragment fragment) {
        super(fragment);
        sensors = new ArrayList<>();
    }

    public void setSensors(LiveData<Sensor>[] sensors) {
        List<LiveData<Sensor>> convertedSensors = new ArrayList<>();
        Collections.addAll(convertedSensors, sensors);

        DiffUtil.DiffResult diffResultOfSensors = getDiffResult(convertedSensors);
        this.sensors = convertedSensors;
        diffResultOfSensors.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<LiveData<Sensor>> sensors) {
        SensorLiveDataDiffCallback profileDiffCallback =
                new SensorLiveDataDiffCallback(this.sensors, sensors);
        return DiffUtil.calculateDiff(profileDiffCallback);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return SensorFragment.withSensor(position);
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }
}
