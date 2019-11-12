package com.blumek.dymek.thermometerProfiles.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.CreationSensorSettingsListItemBinding;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.google.common.collect.Lists;

import java.util.List;

public class SensorsSettingsAdapter extends RecyclerView.Adapter<SensorsSettingsAdapter.ViewHolder> {
    private List<SensorSettings> sensorsSettings;

    public SensorsSettingsAdapter() {
        sensorsSettings = Lists.newArrayList();
    }

    public void setSensorsSettings(List<SensorSettings> sensorsSettings) {
        this.sensorsSettings = sensorsSettings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CreationSensorSettingsListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.creation_sensor_settings_list_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SensorSettings sensorSettings = sensorsSettings.get(position);
        viewHolder.bind(sensorSettings);
    }

    @Override
    public int getItemCount() {
        return sensorsSettings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CreationSensorSettingsListItemBinding binding;

        ViewHolder(CreationSensorSettingsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.sensorsSettings, obj);
            binding.executePendingBindings();
        }
    }
}