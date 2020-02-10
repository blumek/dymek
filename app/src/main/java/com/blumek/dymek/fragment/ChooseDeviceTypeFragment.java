package com.blumek.dymek.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.blumek.dymek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseDeviceTypeFragment extends Fragment {


    public ChooseDeviceTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_device_type, container, false);
    }

}
