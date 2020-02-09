package com.blumek.dymek.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.viewModel.CreationThermometerProfileFragmentViewModel;
import com.blumek.dymek.viewModel.PersistenceThermometerProfileFragmentViewModel;

public class CreationThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel() {
        return new ViewModelProvider(this)
                .get(CreationThermometerProfileFragmentViewModel.class);
    }
}
