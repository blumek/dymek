package com.blumek.dymek.thermometer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.ThermometerFragmentBinding;
import com.blumek.dymek.thermometer.adapters.SensorAdapter;
import com.blumek.dymek.thermometer.viewModels.ThermometerViewModel;


public class ThermometerFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_fragment,
                container, false);

        ThermometerViewModel viewModel = ViewModelProviders.of(this)
                .get(ThermometerViewModel.class);
        SensorAdapter sensorAdapter = new SensorAdapter(this);
        viewModel.getThermometer().observe(this, thermometer ->
                sensorAdapter.setSensors(thermometer.getSensors()));

        binding.setAdapter(sensorAdapter);

        return binding.getRoot();
    }

}
