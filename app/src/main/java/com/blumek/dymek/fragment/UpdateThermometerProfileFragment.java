package com.blumek.dymek.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.viewModel.PersistenceThermometerProfileFragmentViewModel;
import com.blumek.dymek.viewModel.factory.UpdateThermometerProfileViewModelFactory;


public class UpdateThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel() {
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
                .get(PersistenceThermometerProfileFragmentViewModel.class);
    }
}
