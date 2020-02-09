package com.blumek.dymek.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.viewModel.CreationThermometerProfileViewModel;
import com.blumek.dymek.viewModel.PersistenceThermometerProfileViewModel;

public class CreationThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileViewModel getPersistenceViewModel() {
        return new ViewModelProvider(this)
                .get(CreationThermometerProfileViewModel.class);
    }
}
