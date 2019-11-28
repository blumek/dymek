package com.blumek.dymek.thermometer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.blumek.dymek.thermometer.adapters.diffUtils.SensorLiveDataDiffCallback;
import com.blumek.dymek.thermometer.fragments.SensorFragment;
import com.blumek.dymek.thermometer.models.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter extends FragmentStateAdapter {
    private List<MutableLiveData<Sensor>> sensors;

    public SensorAdapter(@NonNull Fragment fragment) {
        super(fragment);
        sensors = new ArrayList<>();
    }

    public void updateSensors(List<MutableLiveData<Sensor>> sensors) {
        DiffUtil.DiffResult diffResultOfSensors = getDiffResult(sensors);

        this.sensors = sensors;
        diffResultOfSensors.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<MutableLiveData<Sensor>> sensors) {
        SensorLiveDataDiffCallback profileDiffCallback =
                new SensorLiveDataDiffCallback(this.sensors, sensors);
        return DiffUtil.calculateDiff(profileDiffCallback);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new SensorFragment(sensors.get(position));
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }
}
