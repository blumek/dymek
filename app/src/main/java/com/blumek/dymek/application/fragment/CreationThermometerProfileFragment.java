package com.blumek.dymek.application.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.CreationThermometerProfileViewModel;
import com.blumek.dymek.application.viewModel.PersistenceThermometerProfileViewModel;

public class CreationThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileViewModel getPersistenceViewModel() {
        return new ViewModelProvider(this)
                .get(CreationThermometerProfileViewModel.class);
    }
}
