package com.blumek.dymek.devices.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.ScanDevicesFragmentBinding;
import com.blumek.dymek.devices.adapters.ScanDeviceAdapter;
import com.blumek.dymek.devices.viewModels.ScanDevicesViewModel;


public class ScanDevicesFragment extends Fragment {
    private ScanDeviceAdapter scanDeviceAdapter;
    private ScanDevicesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ScanDevicesFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.scan_devices_fragment,
                container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(scanDeviceAdapter);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ScanDevicesViewModel.class);
        scanDeviceAdapter = new ScanDeviceAdapter();

        viewModel.getDevices().observe(this, devices ->
                scanDeviceAdapter.updateDevices(devices));
    }

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
