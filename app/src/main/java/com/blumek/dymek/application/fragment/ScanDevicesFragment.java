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
import com.blumek.dymek.application.adapter.ScanDeviceAdapter;
import com.blumek.dymek.application.viewModel.ScanDevicesViewModel;
import com.blumek.dymek.databinding.ScanDevicesFragmentBinding;


public abstract class ScanDevicesFragment extends Fragment {
    private ScanDeviceAdapter scanDeviceAdapter;
    private ScanDevicesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ScanDevicesFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.scan_devices_fragment,
                container, false);

        observeDevices();

        binding.setViewModel(viewModel);
        binding.setAdapter(scanDeviceAdapter);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void observeDevices() {
        viewModel.getDevices().observe(getViewLifecycleOwner(), devices ->
                scanDeviceAdapter.updateDevices(devices));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        scanDeviceAdapter = new ScanDeviceAdapter(viewModel);
    }

    abstract ScanDevicesViewModel getViewModel();

    @Override
    public void onResume() {
        super.onResume();

        if (viewModel.isInitialRun()){
            viewModel.initialRun();
            viewModel.startScanning();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopScanning();
    }
}
