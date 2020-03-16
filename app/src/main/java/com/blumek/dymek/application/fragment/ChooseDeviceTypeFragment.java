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
import com.blumek.dymek.application.viewModel.ChooseDeviceTypeViewModel;
import com.blumek.dymek.databinding.FragmentChooseDeviceTypeBinding;


public class ChooseDeviceTypeFragment extends Fragment {
    private ChooseDeviceTypeViewModel viewModel;

    public ChooseDeviceTypeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChooseDeviceTypeBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_choose_device_type,
                        container, false);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChooseDeviceTypeViewModel.class);
    }
}
