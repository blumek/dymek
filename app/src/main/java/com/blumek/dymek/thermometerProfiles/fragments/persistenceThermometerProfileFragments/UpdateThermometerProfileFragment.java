package com.blumek.dymek.thermometerProfiles.fragments.persistenceThermometerProfileFragments;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.viewModels.factories.PersistenceViewModelFactory;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.PersistenceThermometerProfileFragmentViewModel;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.UpdateThermometerProfileFragmentViewModel;


public class UpdateThermometerProfileFragment extends PersistenceThermometerProfileFragment {
    @Override
    public PersistenceThermometerProfileFragmentViewModel getPersistenceViewModel() {
        ThermometerProfile thermometerProfile = UpdateThermometerProfileFragmentArgs.fromBundle(getArguments()).getThermometerProfile();

        PersistenceViewModelFactory persistenceViewModelFactory =
                new PersistenceViewModelFactory(getActivity().getApplication(),
                        thermometerProfile.getMetadata(), thermometerProfile.getSensorSettings());

        return persistenceViewModelFactory.create(UpdateThermometerProfileFragmentViewModel.class);
    }
}
