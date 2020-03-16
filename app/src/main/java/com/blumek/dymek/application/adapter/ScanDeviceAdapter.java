package com.blumek.dymek.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.application.adapter.diffUtil.DeviceDiffCallback;
import com.blumek.dymek.application.viewModel.ScanDevicesViewModel;
import com.blumek.dymek.databinding.ScanDeviceListItemBinding;
import com.blumek.dymek.model.device.Device;
import com.google.common.collect.Lists;

import java.util.List;

public class ScanDeviceAdapter extends RecyclerView.Adapter<ScanDeviceAdapter.ViewHolder> {
    private ScanDevicesViewModel viewModel;
    private List<Device> devices;

    public ScanDeviceAdapter(ScanDevicesViewModel viewModel) {
        this.viewModel = viewModel;
        setUpDevicesList();
    }

    private void setUpDevicesList() {
        devices = Lists.newArrayList();
    }

    public void updateDevices(List<Device> devices) {
        DiffUtil.DiffResult diffResultOfDevices = getDiffResult(devices);

        this.devices = devices;
        diffResultOfDevices.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<Device> devices) {
        DeviceDiffCallback profileDiffCallback =
                new DeviceDiffCallback(this.devices, devices);
        return DiffUtil.calculateDiff(profileDiffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ScanDeviceListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.scan_device_list_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Device device = devices.get(position);
        viewHolder.bind(device, viewModel);
    }

    @Override
    public int getItemCount() {
        return devices != null ? devices.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ScanDeviceListItemBinding binding;

        ViewHolder(ScanDeviceListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Device device, ScanDevicesViewModel viewModel) {
            binding.setViewModel(viewModel);
            binding.setDevice(device);
            binding.executePendingBindings();
        }
    }
}