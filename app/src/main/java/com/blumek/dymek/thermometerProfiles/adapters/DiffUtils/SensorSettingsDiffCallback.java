package com.blumek.dymek.thermometerProfiles.adapters.DiffUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;

import java.util.List;

public class SensorSettingsDiffCallback extends DiffUtil.Callback {
    private List<SensorSettings> oldSensorsSettings;
    private List<SensorSettings> newSensorsSettings;

    public SensorSettingsDiffCallback(List<SensorSettings> oldSensorsSettings,
                                      List<SensorSettings> newSensorsSettings) {
        this.oldSensorsSettings = oldSensorsSettings;
        this.newSensorsSettings = newSensorsSettings;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldSensorsSettings.size();
    }

    @Override
    public int getNewListSize() {
        return newSensorsSettings.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        SensorSettings oldSensorSettings = oldSensorsSettings.get(oldItemPosition);
        SensorSettings newSensorSettings = newSensorsSettings.get(newItemPosition);

        return oldSensorSettings.getId() == newSensorSettings.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldSensorsSettings.get(oldItemPosition)
                .equals(newSensorsSettings.get(newItemPosition));
    }
}
