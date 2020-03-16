package com.blumek.dymek.application.adapter.diffUtil;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;

import java.util.List;

public class ThermometerProfileDiffCallback extends DiffUtil.Callback {
    private List<ThermometerProfile> oldThermometersProfiles;
    private List<ThermometerProfile> newThermometersProfiles;

    public ThermometerProfileDiffCallback(List<ThermometerProfile> oldThermometersProfiles,
                                          List<ThermometerProfile> newThermometersProfiles) {
        this.oldThermometersProfiles = oldThermometersProfiles;
        this.newThermometersProfiles = newThermometersProfiles;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldThermometersProfiles.size();
    }

    @Override
    public int getNewListSize() {
        return newThermometersProfiles.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ThermometerProfile oldThermometerProfile = oldThermometersProfiles.get(oldItemPosition);
        ThermometerProfile newThermometerProfile = newThermometersProfiles.get(newItemPosition);

        if (!hasMetadata(oldThermometerProfile) && !hasMetadata(newThermometerProfile))
            return true;
        if (!hasMetadata(oldThermometerProfile) || !hasMetadata(newThermometerProfile))
            return false;

        return oldThermometerProfile.getMetadata().getId() ==
                newThermometerProfile.getMetadata().getId();
    }

    private boolean hasMetadata(ThermometerProfile thermometerProfile) {
        return thermometerProfile != null && thermometerProfile.getMetadata() != null;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldThermometersProfiles.get(oldItemPosition)
                .equals(newThermometersProfiles.get(newItemPosition));
    }
}
