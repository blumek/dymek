package com.blumek.dymek.thermometer.fragments;

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
import com.blumek.dymek.databinding.SensorFragmentBinding;
import com.blumek.dymek.thermometer.viewModels.ThermometerViewModel;


public class SensorFragment extends Fragment {
    private static final String INDEX_OF_SENSOR_KEY = "INDEX_OF_SENSOR";
    private static final int NO_SENSOR_INDEX_PASSED = -1;

    private SensorFragmentBinding binding;

    public SensorFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_OF_SENSOR_KEY, NO_SENSOR_INDEX_PASSED);
        setArguments(bundle);
    }

    public static SensorFragment withSensor(int indexOfSensor) {
        SensorFragment sensorFragment = new SensorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_OF_SENSOR_KEY, indexOfSensor);
        sensorFragment.setArguments(bundle);
        return sensorFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.sensor_fragment, container, false);

        int indexOfSensor = getArguments().getInt(INDEX_OF_SENSOR_KEY);
        binding.setIndexOfSensor(indexOfSensor);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ThermometerViewModel viewModel = ViewModelProviders.of(getActivity())
                .get(ThermometerViewModel.class);
        binding.setViewModel(viewModel);
        System.out.println("TRYING TO GET THERMOMETER");
        System.out.println(viewModel.getThermometer().getValue());

    }
}
