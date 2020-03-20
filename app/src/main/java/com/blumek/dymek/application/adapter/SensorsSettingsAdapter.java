package com.blumek.dymek.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.application.adapter.diffUtil.SensorSettingsDiffCallback;
import com.blumek.dymek.application.model.ViewSensorSetting;
import com.blumek.dymek.application.viewModel.PersistenceThermometerProfileViewModel;
import com.blumek.dymek.databinding.CreationSensorSettingsListItemBinding;
import com.google.common.collect.Lists;

import java.util.List;

public class SensorsSettingsAdapter extends RecyclerView.Adapter<SensorsSettingsAdapter.ViewHolder> {
    private PersistenceThermometerProfileViewModel viewModel;
    private List<ViewSensorSetting> viewSensorSettings;

    public SensorsSettingsAdapter(PersistenceThermometerProfileViewModel viewModel) {
        this.viewModel = viewModel;
        setUpSensorsSettingsList();
    }

    private void setUpSensorsSettingsList() {
        viewSensorSettings = Lists.newArrayList();
    }

    public void updateSensorsSettings(List<ViewSensorSetting> viewSensorSettings) {
        DiffUtil.DiffResult diffResultOfSensorsSettings = getDiffResult(viewSensorSettings);

        this.viewSensorSettings = viewSensorSettings;
        diffResultOfSensorsSettings.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<ViewSensorSetting> viewSensorSettings) {
        SensorSettingsDiffCallback profileDiffCallback =
                new SensorSettingsDiffCallback(this.viewSensorSettings, viewSensorSettings);
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
        viewHolder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
        return viewSensorSettings != null ? viewSensorSettings.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CreationSensorSettingsListItemBinding binding;

        ViewHolder(CreationSensorSettingsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PersistenceThermometerProfileViewModel viewModel, int index) {
            binding.setViewModel(viewModel);
            binding.setIndex(index);
            binding.executePendingBindings();
        }
    }
}