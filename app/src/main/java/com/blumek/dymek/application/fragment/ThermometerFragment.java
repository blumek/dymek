package com.blumek.dymek.application.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.R;
import com.blumek.dymek.application.adapter.SensorAdapter;
import com.blumek.dymek.application.service.ThermometerService;
import com.blumek.dymek.application.viewModel.ThermometerViewModel;
import com.blumek.dymek.databinding.ThermometerFragmentBinding;


public class ThermometerFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private ThermometerViewModel viewModel;
    private SensorAdapter sensorAdapter;
    private ThermometerService thermometerService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_fragment,
                container, false);

        sensorAdapter = new SensorAdapter(this);
        observeBinder();
        observeThermometer();

        binding.setLifecycleOwner(this);
        binding.setAdapter(sensorAdapter);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void observeBinder() {
        viewModel.getBinder().observe(getViewLifecycleOwner(), binder -> {
            if (binder != null) {
                thermometerService = binder.getService();
                observeDevice();
                observeServiceThermometer();
            }
        });
    }

    private void observeDevice() {
        viewModel.getDevice().observe(getViewLifecycleOwner(), device ->
                thermometerService.setDevice(device));
    }

    private void observeServiceThermometer() {
        thermometerService.getThermometer().observe(getViewLifecycleOwner(), thermometer ->
                viewModel.setThermometer(thermometer));
    }

    private void observeThermometer() {
        viewModel.getThermometer().observe(getViewLifecycleOwner(), thermometer ->
                sensorAdapter.setSensors(thermometer.getSensors()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ThermometerViewModel.class);
        startService();
    }

    private void startService(){
        Log.d(TAG, "Starting thermometer service");
        Intent serviceBindIntent = new Intent(getActivity(), ThermometerService.class);
        ContextCompat.startForegroundService(getActivity(), serviceBindIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        bindService();
    }

    private void bindService(){
        Log.d(TAG, "Binding to thermometer service");
        Intent serviceBindIntent = new Intent(getActivity(), ThermometerService.class);
        getActivity().bindService(serviceBindIntent, viewModel.getServiceConnection(),
                Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService();
    }

    private void unbindService() {
        Log.d(TAG, "Unbinding from thermometer service");
        getActivity().unbindService(viewModel.getServiceConnection());
    }
}
