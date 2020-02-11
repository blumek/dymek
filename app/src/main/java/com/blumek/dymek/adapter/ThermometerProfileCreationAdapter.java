package com.blumek.dymek.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.adapter.diffUtil.ThermometerProfileDiffCallback;
import com.blumek.dymek.databinding.ThermometerProfileCreationListItemBinding;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.viewModel.ThermometerProfileCreationViewModel;
import com.google.common.collect.Lists;

import java.util.List;

public class ThermometerProfileCreationAdapter extends
        RecyclerView.Adapter<ThermometerProfileCreationAdapter.ViewHolder> {
    private ThermometerProfileCreationViewModel viewModel;
    private List<ThermometerProfile> thermometerProfiles;

    public ThermometerProfileCreationAdapter(ThermometerProfileCreationViewModel viewModel) {
        this.viewModel = viewModel;
        setUpThermometerProfilesList();
    }

    private void setUpThermometerProfilesList() {
        thermometerProfiles = Lists.newArrayList();
    }

    public void updateThermometerProfiles(List<ThermometerProfile> thermometerProfiles) {
        DiffUtil.DiffResult diffResultOfThermometersProfiles = getDiffResult(thermometerProfiles);

        this.thermometerProfiles = thermometerProfiles;
        diffResultOfThermometersProfiles.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<ThermometerProfile> thermometerProfiles) {
        ThermometerProfileDiffCallback profileDiffCallback =
                new ThermometerProfileDiffCallback(this.thermometerProfiles, thermometerProfiles);
        return DiffUtil.calculateDiff(profileDiffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ThermometerProfileCreationListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.thermometer_profile_creation_list_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ThermometerProfile thermometerProfile = thermometerProfiles.get(position);
        viewHolder.bind(viewModel, thermometerProfile);
    }

    @Override
    public int getItemCount() {
        return thermometerProfiles != null ? thermometerProfiles.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ThermometerProfileCreationListItemBinding binding;

        ViewHolder(ThermometerProfileCreationListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ThermometerProfileCreationViewModel viewModel,
                  ThermometerProfile thermometerProfile) {
            binding.setViewModel(viewModel);
            binding.setThermometerProfiles(thermometerProfile);
            binding.executePendingBindings();
        }
    }
}