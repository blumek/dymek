package com.blumek.dymek.devices.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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
import com.blumek.dymek.devices.models.BLEDevice;
import com.blumek.dymek.devices.viewModels.ScanDevicesViewModel;


public class ScanDevicesFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private ScanDeviceAdapter scanDeviceAdapter;
    private ScanDevicesViewModel viewModel;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d(TAG, "DISCOVERY STARTED");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d(TAG, "DISCOVERY STOPPED");
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d(TAG, "FOUND DEVICE");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device != null)
                    viewModel.addDevice(new BLEDevice(device.getName(), device.getAddress()));
            }
        }
    };

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

        getActivity().registerReceiver(receiver, getBluetoothDiscoveryFilter());

        if (viewModel.isInitialRun()){
            viewModel.initialRun();
            viewModel.startScanning();
        }
    }

    private IntentFilter getBluetoothDiscoveryFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        return filter;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopScanning();
        getActivity().unregisterReceiver(receiver);
    }
}
