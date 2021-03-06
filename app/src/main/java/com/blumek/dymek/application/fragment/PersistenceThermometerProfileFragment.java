package com.blumek.dymek.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.blumek.dymek.R;
import com.blumek.dymek.application.adapter.SensorsSettingsAdapter;
import com.blumek.dymek.application.viewModel.PersistenceThermometerProfileViewModel;
import com.blumek.dymek.databinding.CreationThermometerProfileFragmentBinding;

public abstract class PersistenceThermometerProfileFragment extends Fragment {
    private PersistenceThermometerProfileViewModel viewModel;
    private SensorsSettingsAdapter sensorsSettingsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreationThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.creation_thermometer_profile_fragment,
                        container, false);

        sensorsSettingsAdapter = new SensorsSettingsAdapter(viewModel);

        binding.setViewModel(viewModel);
        binding.setAdapter(sensorsSettingsAdapter);
        binding.setLifecycleOwner(this);

        observeSensorsSettings();

        return binding.getRoot();
    }

    private void observeSensorsSettings() {
        viewModel.getSensorsSettings().observe(getViewLifecycleOwner(), sensorSettings ->
                sensorsSettingsAdapter.updateSensorsSettings(sensorSettings));
    }

    public abstract PersistenceThermometerProfileViewModel getPersistenceViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getPersistenceViewModel();
    }
}