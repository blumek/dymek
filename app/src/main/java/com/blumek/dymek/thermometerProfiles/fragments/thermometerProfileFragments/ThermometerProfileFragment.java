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
import com.blumek.dymek.thermometerProfiles.adapters.ThermometerProfileMetadataAdapter;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.viewModels.thermometerProfileViewModels.ThermometerProfileFragmentViewModel;

import java.util.List;

public class ThermometerProfileFragment extends Fragment {
    private ThermometerProfileMetadataAdapter thermometerProfileMetadataAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerProfileFragmentViewModel viewModel = ViewModelProviders.of(this).get(ThermometerProfileFragmentViewModel.class);
        thermometerProfileMetadataAdapter = new ThermometerProfileMetadataAdapter();

        ThermometerProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_profile_fragment,
                container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(thermometerProfileMetadataAdapter);
        binding.setLifecycleOwner(this);

        viewModel.getThermometerProfiles().observe(this, new Observer<List<ThermometerProfileMetadata>>() {
            @Override
            public void onChanged(List<ThermometerProfileMetadata> thermometerProfileMetadata) {
                thermometerProfileMetadataAdapter.setThermometerProfileMetadata(thermometerProfileMetadata);
            }
        });

        return binding.getRoot();
    }

}
