package com.blumek.dymek.application.adapter.diffUtil;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;

import java.util.List;

public class SensorSettingsDiffCallback extends DiffUtil.Callback {
    private List<RoomSensorSettings> oldSensorsSettings;
    private List<RoomSensorSettings> newSensorsSettings;

    public SensorSettingsDiffCallback(List<RoomSensorSettings> oldSensorsSettings,
                                      List<RoomSensorSettings> newSensorsSettings) {
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
        RoomSensorSettings oldRoomSensorSettings = oldSensorsSettings.get(oldItemPosition);
        RoomSensorSettings newRoomSensorSettings = newSensorsSettings.get(newItemPosition);

        return equalIdentifiers(oldRoomSensorSettings.getId(), newRoomSensorSettings.getId());
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
