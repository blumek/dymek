package com.blumek.dymek.fragment;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.viewModels.factories.UpdateThermometerProfileViewModelFactory;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.PersistenceThermometerProfileFragmentViewModel;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.UpdateThermometerProfileFragmentViewModel;


public class UpdateThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel() {
        if (getArguments() == null)
            throw new IllegalStateException();

        ThermometerProfile thermometerProfile = UpdateThermometerProfileFragmentArgs.fromBundle(getArguments()).getThermometerProfile();
        if (thermometerProfile == null)
            throw new IllegalStateException();

        UpdateThermometerProfileViewModelFactory updateThermometerProfileViewModelFactory =
                new UpdateThermometerProfileViewModelFactory(getActivity().getApplication(), thermometerProfile);

        return updateThermometerProfileViewModelFactory.create(UpdateThermometerProfileFragmentViewModel.class);
    }
}
