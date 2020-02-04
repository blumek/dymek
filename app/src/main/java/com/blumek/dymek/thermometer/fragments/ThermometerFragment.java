package com.blumek.dymek.thermometer.fragments;

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
import com.blumek.dymek.thermometer.adapters.SensorAdapter;
import com.blumek.dymek.thermometer.services.ThermometerService;
import com.blumek.dymek.thermometer.viewModels.ThermometerViewModel;


public class ThermometerFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private ThermometerViewModel viewModel;
    private ThermometerService thermometerService;
    private SensorAdapter sensorAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.thermometer_fragment,
                container, false);

        viewModel = ViewModelProviders.of(this)
                .get(ThermometerViewModel.class);
        sensorAdapter = new SensorAdapter(this);
        observeThermometer();
        observeBinder();

        binding.setAdapter(sensorAdapter);

        return binding.getRoot();
    }

    private void observeThermometer() {
        viewModel.getThermometer().observe(this, thermometer ->
                sensorAdapter.setSensors(thermometer.getSensors()));
    }

    private void observeBinder() {
        viewModel.getBinder().observe(this, binder -> {
            Log.d(TAG, "onChanged: new binder available.");
            if (binder != null) {
                Log.d(TAG, "onChanged: bound to service.");
                thermometerService = binder.getService();
                thermometerService.setDevice(viewModel.getDevice());
            } else {
                Log.d(TAG, "onChanged: unbound from service");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService();
    }

    @Override
    public void onResume() {
        super.onResume();
        bindService();
    }


    @Override
    public void onPause() {
        super.onPause();
        getActivity().unbindService(viewModel.getServiceConnection());
    }

    private void startService(){
        Intent serviceBindIntent = new Intent(getActivity(), ThermometerService.class);
        ContextCompat.startForegroundService(getActivity(), serviceBindIntent);
    }

    private void bindService(){
        Intent serviceBindIntent = new Intent(getActivity(), ThermometerService.class);
        getActivity().bindService(serviceBindIntent, viewModel.getServiceConnection(),
                Context.BIND_AUTO_CREATE);
    }
}
