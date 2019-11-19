package com.blumek.dymek.thermometerProfiles.fragments.thermometerProfileFragments;

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
import com.blumek.dymek.databinding.ThermometerProfileFragmentBinding;
import com.blumek.dymek.thermometerProfiles.adapters.ThermometerProfileAdapter;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.viewModels.thermometerProfileViewModels.ThermometerProfileFragmentViewModel;

import java.util.List;

public class ThermometerProfileFragment extends Fragment {
    private ThermometerProfileAdapter thermometerProfileAdapter;
    private ThermometerProfileFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(ThermometerProfileFragmentViewModel.class);
        thermometerProfileAdapter = new ThermometerProfileAdapter(viewModel);

        ThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_profile_fragment,
                container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(thermometerProfileAdapter);
        binding.setLifecycleOwner(this);

        observeThermometersProfiles();

        return binding.getRoot();
    }

    private void observeThermometersProfiles() {
        viewModel.getThermometersProfiles().observe(this, new Observer<List<ThermometerProfile>>() {
            @Override
            public void onChanged(List<ThermometerProfile> thermometerProfiles) {
                thermometerProfileAdapter.updateThermometerProfiles(thermometerProfiles);
            }
        });
    }

}
