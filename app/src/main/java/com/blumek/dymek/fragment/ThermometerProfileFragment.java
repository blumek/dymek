package com.blumek.dymek.fragment;

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
import com.blumek.dymek.adapter.ThermometerProfileAdapter;
import com.blumek.dymek.databinding.ThermometerProfileFragmentBinding;
import com.blumek.dymek.viewModel.ThermometerProfileViewModel;


public class ThermometerProfileFragment extends Fragment {
    private ThermometerProfileAdapter thermometerProfileAdapter;
    private ThermometerProfileViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_profile_fragment,
                container, false);

        thermometerProfileAdapter = new ThermometerProfileAdapter(viewModel);

        observeThermometersProfiles();

        binding.setViewModel(viewModel);
        binding.setAdapter(thermometerProfileAdapter);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void observeThermometersProfiles() {
        viewModel.getThermometersProfiles().observe(getViewLifecycleOwner(), thermometerProfiles ->
                thermometerProfileAdapter.updateThermometerProfiles(thermometerProfiles));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this)
                .get(ThermometerProfileViewModel.class);
    }
}
