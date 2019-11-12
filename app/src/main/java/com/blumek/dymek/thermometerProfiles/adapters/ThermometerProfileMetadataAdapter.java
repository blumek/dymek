package com.blumek.dymek.thermometerProfiles.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.google.common.collect.Lists;

import java.util.List;

public class ThermometerProfileMetadataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ThermometerProfileMetadata> thermometerProfileMetadata;

    public ThermometerProfileMetadataAdapter() {
        thermometerProfileMetadata = Lists.newArrayList();
    }

    public void setThermometerProfileMetadata(List<ThermometerProfileMetadata> thermometerProfileMetadata) {
        this.thermometerProfileMetadata = thermometerProfileMetadata;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thermometer_profile_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((ViewHolder)viewHolder).profileName.setText(thermometerProfileMetadata.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return thermometerProfileMetadata.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView profileName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileName = itemView.findViewById(R.id.profile_name_edit_text);
        }
    }
}