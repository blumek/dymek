package com.blumek.dymek.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.R;
import com.blumek.dymek.application.adapter.ThermometerProfileManagementAdapter;
import com.blumek.dymek.application.viewModel.ThermometerProfileManagementViewModel;
import com.blumek.dymek.databinding.ThermometerProfileFragmentBinding;


public class ThermometerProfileManagementFragment extends Fragment {
    private ThermometerProfileManagementAdapter thermometerProfileManagementAdapter;
    private ThermometerProfileManagementViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_profile_fragment,
                container, false);

        thermometerProfileManagementAdapter = new ThermometerProfileManagementAdapter(viewModel);

        observeThermometersProfiles();

        binding.setViewModel(viewModel);
        binding.setAdapter(thermometerProfileManagementAdapter);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void observeThermometersProfiles() {
        viewModel.getThermometersProfiles().observe(getViewLifecycleOwner(), thermometerProfiles ->
                thermometerProfileManagementAdapter.updateThermometerProfiles(thermometerProfiles));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this)
                .get(ThermometerProfileManagementViewModel.class);
    }
}
