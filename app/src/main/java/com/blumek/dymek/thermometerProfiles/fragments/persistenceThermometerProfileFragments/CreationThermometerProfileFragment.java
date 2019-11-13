package com.blumek.dymek.thermometerProfiles.fragments.persistenceThermometerProfileFragments;

import androidx.lifecycle.ViewModelProviders;

import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.CreationThermometerProfileFragmentViewModel;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.PersistenceThermometerProfileFragmentViewModel;

public class CreationThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel() {
        return ViewModelProviders.of(this)
                .get(CreationThermometerProfileFragmentViewModel.class);
    }
}
