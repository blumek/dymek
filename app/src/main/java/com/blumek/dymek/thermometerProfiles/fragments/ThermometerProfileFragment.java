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
import com.blumek.dymek.thermometerProfiles.adapters.ThermometerProfileAdapter;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.viewmodels.ThermometerProfileViewModel;

import java.util.List;

public class ThermometerProfileFragment extends Fragment {
    private ThermometerProfileViewModel thermometerProfileViewModel;
    private RecyclerView thermometerProfileRecyclerView;
    private ThermometerProfileAdapter thermometerProfileAdapter;

    public static ThermometerProfileFragment newInstance() {
        return new ThermometerProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        thermometerProfileViewModel = ViewModelProviders.of(this).get(ThermometerProfileViewModel.class);
        thermometerProfileAdapter = new ThermometerProfileAdapter();
        thermometerProfileViewModel.getThermometerProfiles().observe(this, new Observer<List<ThermometerProfile>>() {
            @Override
            public void onChanged(List<ThermometerProfile> thermometerProfiles) {
                thermometerProfileAdapter.setThermometerProfiles(thermometerProfiles);
            }
        });

        View view = inflater.inflate(R.layout.thermometer_profile_fragment, container, false);
        thermometerProfileRecyclerView = view.findViewById(R.id.profile_recycler_view);
        thermometerProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        thermometerProfileRecyclerView.setAdapter(thermometerProfileAdapter);
        thermometerProfileRecyclerView.setHasFixedSize(true);
        return view;
    }

}
