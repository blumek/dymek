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

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.SensorFragmentBinding;
import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.viewModels.SensorViewModel;
import com.blumek.dymek.thermometer.viewModels.factories.SensorViewModelFactory;

public class SensorFragment extends Fragment {
    private SensorViewModel viewModel;

    public SensorFragment(LiveData<Sensor> sensor) {
        SensorViewModelFactory factory = new SensorViewModelFactory(sensor);
        viewModel = factory.create(SensorViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SensorFragmentBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.sensor_fragment, container, false);

        binding.setLifecycleOwner(this);
        viewModel.getSensor().observe(this, binding::setSensor);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
