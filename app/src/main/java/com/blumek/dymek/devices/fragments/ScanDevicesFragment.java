package com.blumek.dymek.devices.fragments;

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
import com.blumek.dymek.databinding.ScanDevicesFragmentBinding;
import com.blumek.dymek.devices.adapters.ScanDeviceAdapter;
import com.blumek.dymek.devices.models.BLEDevice;
import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.viewModels.ScanDevicesViewModel;

import java.util.List;

public class ScanDevicesFragment extends Fragment {
    private ScanDeviceAdapter scanDeviceAdapter;
    private ScanDevicesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(ScanDevicesViewModel.class);
        scanDeviceAdapter = new ScanDeviceAdapter();

        ScanDevicesFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.scan_devices_fragment,
                container, false);

        binding.setViewModel(viewModel);
        binding.setAdapter(scanDeviceAdapter);
        binding.setLifecycleOwner(this);

        viewModel.getDevices().observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                scanDeviceAdapter.updateDevices(devices);
            }
        });

        viewModel.addDevice(new BLEDevice("Test 1"));
        viewModel.addDevice(new BLEDevice("Test 2"));
        viewModel.addDevice(new BLEDevice("Test 3"));

        return binding.getRoot();
    }

}
