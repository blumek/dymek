package com.blumek.dymek.application.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.PersistenceThermometerProfileViewModel;
import com.blumek.dymek.application.viewModel.factory.UpdateThermometerProfileViewModelFactory;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;


public class UpdateThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileViewModel getPersistenceViewModel() {
        if (getArguments() == null)
            throw new IllegalStateException();

        ThermometerProfile thermometerProfile = UpdateThermometerProfileFragmentArgs
                .fromBundle(getArguments()).getThermometerProfile();
        if (thermometerProfile == null)
            throw new IllegalStateException();

        UpdateThermometerProfileViewModelFactory updateThermometerProfileViewModelFactory =
                new UpdateThermometerProfileViewModelFactory(getActivity().getApplication(),
                        thermometerProfile);

        return new ViewModelProvider(this, updateThermometerProfileViewModelFactory)
                .get(PersistenceThermometerProfileViewModel.class);
    }
}
