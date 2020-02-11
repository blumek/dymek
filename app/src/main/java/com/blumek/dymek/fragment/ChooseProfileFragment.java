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
import com.blumek.dymek.adapter.ThermometerProfileChooseAdapter;
import com.blumek.dymek.databinding.ChooseProfileFragmentBinding;
import com.blumek.dymek.viewModel.ChooseProfileViewModel;

public class ChooseProfileFragment extends Fragment {
    private ThermometerProfileChooseAdapter thermometerProfileChooseAdapter;
    private ChooseProfileViewModel viewModel;

    public static ChooseProfileFragment newInstance() {
        return new ChooseProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ChooseProfileFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.choose_profile_fragment,
                        container, false);

        thermometerProfileChooseAdapter = new ThermometerProfileChooseAdapter(viewModel);

        observeThermometersProfiles();

        binding.setViewModel(viewModel);
        binding.setAdapter(thermometerProfileChooseAdapter);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void observeThermometersProfiles() {
        viewModel.getThermometersProfiles().observe(getViewLifecycleOwner(), thermometerProfiles ->
                thermometerProfileChooseAdapter.updateThermometerProfiles(thermometerProfiles));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChooseProfileViewModel.class);
    }
}
