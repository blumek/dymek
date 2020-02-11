package com.blumek.dymek.fragment;


import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.viewModel.BLEScanDevicesViewModel;
import com.blumek.dymek.viewModel.ScanDevicesViewModel;


public class BLEScanDevicesFragment extends ScanDevicesFragment {
    ScanDevicesViewModel getViewModel() {
        return new ViewModelProvider(this).get(BLEScanDevicesViewModel.class);
    }
}
