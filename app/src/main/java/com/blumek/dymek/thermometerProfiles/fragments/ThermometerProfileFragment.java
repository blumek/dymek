package com.blumek.dymek.thermometerProfiles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.dymek.R;
import com.blumek.dymek.thermometerProfiles.adapters.ThermometerProfileMetadataAdapter;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.viewmodels.ThermometerProfileFragmentViewModel;

import java.util.List;

public class ThermometerProfileFragment extends Fragment {
    private ThermometerProfileMetadataAdapter thermometerProfileMetadataAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ThermometerProfileFragmentViewModel viewModel = ViewModelProviders.of(this).get(ThermometerProfileFragmentViewModel.class);
        thermometerProfileMetadataAdapter = new ThermometerProfileMetadataAdapter();
        viewModel.getThermometerProfiles().observe(this, new Observer<List<ThermometerProfileMetadata>>() {
            @Override
            public void onChanged(List<ThermometerProfileMetadata> thermometerProfileMetadata) {
                thermometerProfileMetadataAdapter.setThermometerProfileMetadata(thermometerProfileMetadata);
            }
        });

        View view = inflater.inflate(R.layout.thermometer_profile_fragment, container, false);
        RecyclerView thermometerProfileRecyclerView = view.findViewById(R.id.profile_recycler_view);
        thermometerProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        thermometerProfileRecyclerView.setAdapter(thermometerProfileMetadataAdapter);
        thermometerProfileRecyclerView.setHasFixedSize(true);
        return view;
    }

}
