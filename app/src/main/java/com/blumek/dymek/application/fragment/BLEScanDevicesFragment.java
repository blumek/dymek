package com.blumek.dymek.application.fragment;


import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.BLEScanDevicesViewModel;
import com.blumek.dymek.application.viewModel.ScanDevicesViewModel;


public class BLEScanDevicesFragment extends ScanDevicesFragment {
    ScanDevicesViewModel getViewModel() {
        return new ViewModelProvider(this).get(BLEScanDevicesViewModel.class);
    }
}
