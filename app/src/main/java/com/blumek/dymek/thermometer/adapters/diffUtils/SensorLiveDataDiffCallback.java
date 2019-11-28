package com.blumek.dymek.thermometer.adapters.diffUtils;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.thermometer.models.Sensor;

import java.util.List;

public class SensorLiveDataDiffCallback extends DiffUtil.Callback {
    private List<MutableLiveData<Sensor>> oldSensors;
    private List<MutableLiveData<Sensor>> newSensors;

    public SensorLiveDataDiffCallback(List<MutableLiveData<Sensor>> oldSensors,
                                      List<MutableLiveData<Sensor>> newSensors) {
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
        return oldSensors.get(oldItemPosition).getValue().getName()
                .equals(newSensors.get(newItemPosition).getValue().getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldSensors.get(oldItemPosition)
                .equals(newSensors.get(newItemPosition));
    }
}
