package com.blumek.dymek.devices.adapters.diffUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.devices.models.Device;

import java.util.List;

public class DeviceDiffCallback extends DiffUtil.Callback {
    private List<Device> oldDevices;
    private List<Device> newDevices;

    public DeviceDiffCallback(List<Device> oldDevices,
                              List<Device> newDevices) {
        this.oldDevices = oldDevices;
        this.newDevices = newDevices;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldDevices.size();
    }

    @Override
    public int getNewListSize() {
        return newDevices.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDevices.get(oldItemPosition)
                .equals(newDevices.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDevices.get(oldItemPosition)
                .equals(newDevices.get(newItemPosition));
    }
}
