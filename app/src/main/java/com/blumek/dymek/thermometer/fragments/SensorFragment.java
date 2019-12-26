package com.blumek.dymek.thermometer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.SensorFragmentBinding;
import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.viewModels.SensorViewModel;


public class SensorFragment extends Fragment {
    private LiveData<Sensor> sensor;

    public static SensorFragment withSensor(LiveData<Sensor> sensor) {
        SensorFragment sensorFragment = new SensorFragment();
        sensorFragment.setSensor(sensor);
        return sensorFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SensorFragmentBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.sensor_fragment, container, false);

        SensorViewModel viewModel = ViewModelProviders.of(this).get(SensorViewModel.class);
        if (sensor != null)
            viewModel.setSensor(sensor);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    public void setSensor(LiveData<Sensor> sensor) {
        this.sensor = sensor;
    }
}
