package com.blumek.dymek.thermometerProfiles.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blumek.dymek.R;
import com.blumek.dymek.thermometerProfiles.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.viewmodels.ThermometerProfileViewModel;
import com.blumek.dymek.thermometerProfiles.adapters.ThermometerProfileAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class ThermometerProfileFragment extends Fragment {
    private static final String TAG = "ThermometerProfileFragment";
    private RecyclerView profileRecyclerView;
    private FloatingActionButton fab;
    private ThermometerProfileAdapter thermometerProfileAdapter;
    private ThermometerProfileViewModel thermometerProfileViewModel;

    public static ThermometerProfileFragment newInstance() {
        return new ThermometerProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thermometer_profile_fragment, container, false);

        profileRecyclerView = view.findViewById(R.id.profile_recycler_view);
        fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thermometerProfileViewModel.add(new ThermometerProfile("Pepege - " + new Date()));
            }
        });

        thermometerProfileViewModel = ViewModelProviders.of(this).get(ThermometerProfileViewModel.class);
        thermometerProfileAdapter = new ThermometerProfileAdapter();
        thermometerProfileViewModel.getThermometerProfiles().observe(this, new Observer<List<ThermometerProfile>>() {
            @Override
            public void onChanged(List<ThermometerProfile> thermometerProfiles) {
                thermometerProfileAdapter.setThermometerProfiles(thermometerProfiles);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        profileRecyclerView.setLayoutManager(layoutManager);
        profileRecyclerView.setAdapter(thermometerProfileAdapter);
        return view;
    }

}
