package com.blumek.dymek.thermometerProfiles.fragments.persistenceThermometerProfileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.CreationThermometerProfileFragmentBinding;
import com.blumek.dymek.thermometerProfiles.adapters.SensorsSettingsAdapter;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.PersistenceThermometerProfileFragmentViewModel;

import java.util.List;

public abstract class PersistenceThermometerProfileFragment extends Fragment {
    private PersistenceThermometerProfileFragmentViewModel viewModel;
    private SensorsSettingsAdapter sensorsSettingsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = getPersistenceViewModel();
        sensorsSettingsAdapter = new SensorsSettingsAdapter(viewModel);

        CreationThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.creation_thermometer_profile_fragment,
                        container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(sensorsSettingsAdapter);
        binding.setLifecycleOwner(this);

        observeSensorsSettings();

        return binding.getRoot();
    }

    private void observeSensorsSettings() {
        viewModel.getSensorsSettings().observe(this, new Observer<List<SensorSettings>>() {
            @Override
            public void onChanged(List<SensorSettings> sensorSettings) {
                sensorsSettingsAdapter.updateSensorsSettings(sensorSettings);
            }
        });
    }

    public abstract PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel();
}