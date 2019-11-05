package com.blumek.dymek.thermometerProfiles.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;

import java.util.ArrayList;
import java.util.List;

public class ThermometerProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ThermometerProfile> thermometerProfiles;

    public ThermometerProfileAdapter() {
        thermometerProfiles = new ArrayList<>();
    }

    public void setThermometerProfiles(List<ThermometerProfile> thermometerProfiles) {
        this.thermometerProfiles = thermometerProfiles;
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
        ((ViewHolder)viewHolder).profileName.setText(thermometerProfiles.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return thermometerProfiles.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView profileName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileName = itemView.findViewById(R.id.profile_name);
        }
    }
}