package com.blumek.dymek.application.adapter.diffUtil;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.application.model.ViewSensorSetting;

import java.util.List;

public class SensorSettingsDiffCallback extends DiffUtil.Callback {
    private List<ViewSensorSetting> oldSensorsSettings;
    private List<ViewSensorSetting> newSensorsSettings;

    public SensorSettingsDiffCallback(List<ViewSensorSetting> oldSensorsSettings,
                                      List<ViewSensorSetting> newSensorsSettings) {
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
        ViewSensorSetting oldSensorSettings = oldSensorsSettings.get(oldItemPosition);
        ViewSensorSetting newSensorSettings = newSensorsSettings.get(newItemPosition);

        return equalIdentifiers(oldSensorSettings.getId(), newSensorSettings.getId());
    }

    private boolean equalIdentifiers(String oldRoomSensorSettingsId, String newRoomSensorSettingsId) {
        return oldRoomSensorSettingsId != null &&
                oldRoomSensorSettingsId.equals(newRoomSensorSettingsId);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldSensorsSettings.get(oldItemPosition)
                .equals(newSensorsSettings.get(newItemPosition));
    }
}
