package com.blumek.dymek.fragment;

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
import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.ThermometerFragmentBinding;
import com.blumek.dymek.adapter.SensorAdapter;
import com.blumek.dymek.thermometer.services.ThermometerService;
import com.blumek.dymek.viewModel.ThermometerViewModel;


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
        binding.setAdapter(sensorAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity())
                .get(ThermometerViewModel.class);
        observeBinder();
        observeThermometer();
    }

    private void observeBinder() {
        viewModel.getBinder().observe(this, binder -> {
            if (binder != null) {
                thermometerService = binder.getService();
                observeDevice();
                observeServiceThermometer();
            }
        });
    }

    private void observeDevice() {
        viewModel.getDevice().observe(this, device ->
                thermometerService.setDevice(device));
    }

    private void observeServiceThermometer() {
        thermometerService.getThermometer().observe(this, thermometer ->
                viewModel.setThermometer(thermometer));
    }

    private void observeThermometer() {
        viewModel.getThermometer().observe(this, thermometer ->
                sensorAdapter.setSensors(thermometer.getSensors()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
