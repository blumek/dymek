package com.blumek.dymek.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.viewModel.ScanDevicesViewModel;
import com.blumek.dymek.viewModel.SimulationScanDevicesViewModel;


public class SimulationScanDevicesFragment extends ScanDevicesFragment {
    ScanDevicesViewModel getViewModel() {
        return new ViewModelProvider(this).get(SimulationScanDevicesViewModel.class);
    }
}
