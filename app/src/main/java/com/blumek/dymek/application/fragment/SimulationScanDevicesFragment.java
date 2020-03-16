package com.blumek.dymek.application.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.ScanDevicesViewModel;
import com.blumek.dymek.application.viewModel.SimulationScanDevicesViewModel;


public class SimulationScanDevicesFragment extends ScanDevicesFragment {
    ScanDevicesViewModel getViewModel() {
        return new ViewModelProvider(this).get(SimulationScanDevicesViewModel.class);
    }
}
