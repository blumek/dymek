package com.blumek.dymek.adapter.diffUtil;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.thermometer.models.Sensor;

import java.util.List;

public class SensorLiveDataDiffCallback extends DiffUtil.Callback {
    private List<LiveData<Sensor>> oldSensors;
    private List<LiveData<Sensor>> newSensors;

    public SensorLiveDataDiffCallback(List<LiveData<Sensor>> oldSensors,
                                      List<LiveData<Sensor>> newSensors) {
        this.oldSensors = oldSensors;
        this.newSensors = newSensors;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldSensors.size();
    }

    @Override
    public int getNewListSize() {
        return newSensors.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(oldItemPosition, newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldSensors.get(oldItemPosition)
                .equals(newSensors.get(newItemPosition));
    }
}
