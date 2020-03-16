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
import com.blumek.dymek.application.viewModel.ThermometerViewModel;
import com.blumek.dymek.databinding.SensorFragmentBinding;


public class SensorFragment extends Fragment {
    private static final String INDEX_OF_SENSOR_KEY = "INDEX_OF_SENSOR";
    private static final int NO_SENSOR_INDEX_PASSED = -1;

    private ThermometerViewModel viewModel;
    private int indexOfSensor;

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
        SensorFragmentBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.sensor_fragment, container, false);

        binding.setIndexOfSensor(indexOfSensor);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity())
                .get(ThermometerViewModel.class);

        indexOfSensor = getArguments().getInt(INDEX_OF_SENSOR_KEY);
    }
}
