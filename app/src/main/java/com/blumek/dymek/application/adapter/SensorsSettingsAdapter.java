package com.blumek.dymek.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.application.adapter.diffUtil.SensorSettingsDiffCallback;
import com.blumek.dymek.application.viewModel.PersistenceThermometerProfileViewModel;
import com.blumek.dymek.databinding.CreationSensorSettingsListItemBinding;
import com.google.common.collect.Lists;

import java.util.List;

public class SensorsSettingsAdapter extends RecyclerView.Adapter<SensorsSettingsAdapter.ViewHolder> {
    private PersistenceThermometerProfileViewModel viewModel;
    private List<RoomSensorSettings> sensorsSettings;

    public SensorsSettingsAdapter(PersistenceThermometerProfileViewModel viewModel) {
        this.viewModel = viewModel;
        setUpSensorsSettingsList();
    }

    private void setUpSensorsSettingsList() {
        sensorsSettings = Lists.newArrayList();
    }

    public void updateSensorsSettings(List<RoomSensorSettings> sensorsSettings) {
        DiffUtil.DiffResult diffResultOfSensorsSettings = getDiffResult(sensorsSettings);

        this.sensorsSettings = sensorsSettings;
        diffResultOfSensorsSettings.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<RoomSensorSettings> sensorsSettings) {
        SensorSettingsDiffCallback profileDiffCallback =
                new SensorSettingsDiffCallback(this.sensorsSettings, sensorsSettings);
        return DiffUtil.calculateDiff(profileDiffCallback);
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
        RoomSensorSettings roomSensorSettings = sensorsSettings.get(position);
        viewHolder.bind(viewModel, roomSensorSettings);
    }

    @Override
    public int getItemCount() {
        return sensorsSettings != null ? sensorsSettings.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CreationSensorSettingsListItemBinding binding;

        ViewHolder(CreationSensorSettingsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PersistenceThermometerProfileViewModel viewModel,
                  RoomSensorSettings roomSensorSettings) {
            binding.setViewModel(viewModel);
            binding.setSensorsSettings(roomSensorSettings);
            binding.executePendingBindings();
        }
    }
}