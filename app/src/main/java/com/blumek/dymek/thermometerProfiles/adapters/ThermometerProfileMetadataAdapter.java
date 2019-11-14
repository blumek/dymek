package com.blumek.dymek.thermometerProfiles.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.databinding.ThermometerProfileListItemBinding;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.google.common.collect.Lists;

import java.util.List;

public class ThermometerProfileMetadataAdapter extends
        RecyclerView.Adapter<ThermometerProfileMetadataAdapter.ViewHolder> {
    private List<ThermometerProfileMetadata> thermometerProfilesMetadata;

    public ThermometerProfileMetadataAdapter() {
        thermometerProfilesMetadata = Lists.newArrayList();
    }

    public void setThermometerProfilesMetadata(List<ThermometerProfileMetadata> thermometerProfilesMetadata) {
        this.thermometerProfilesMetadata = thermometerProfilesMetadata;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ThermometerProfileListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.thermometer_profile_list_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ThermometerProfileMetadata thermometerProfileMetadata = thermometerProfilesMetadata.get(position);
        viewHolder.bind(thermometerProfileMetadata);
    }

    @Override
    public int getItemCount() {
        return thermometerProfilesMetadata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ThermometerProfileListItemBinding binding;

        ViewHolder(ThermometerProfileListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.thermometerProfilesMetadata, obj);
            binding.executePendingBindings();
        }
    }
}