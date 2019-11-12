package com.blumek.dymek.thermometerProfiles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.CreationThermometerProfileFragmentBinding;
import com.blumek.dymek.thermometerProfiles.adapters.SensorsSettingsAdapter;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.viewmodels.CreationThermometerProfileFragmentViewModel;

import java.util.List;

public class CreationThermometerProfileFragment extends Fragment {
    private SensorsSettingsAdapter sensorsSettingsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreationThermometerProfileFragmentViewModel viewModel =
                ViewModelProviders.of(this)
                .get(CreationThermometerProfileFragmentViewModel.class);
        sensorsSettingsAdapter = new SensorsSettingsAdapter();

        CreationThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.creation_thermometer_profile_fragment,
                        container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(sensorsSettingsAdapter);
        binding.setLifecycleOwner(this);

        viewModel.getSensorsSettings().observe(this, new Observer<List<SensorSettings>>() {
            @Override
            public void onChanged(List<SensorSettings> sensorSettings) {
                sensorsSettingsAdapter.setSensorsSettings(sensorSettings);
            }
        });

        return binding.getRoot();
    }

}
