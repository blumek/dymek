package com.blumek.dymek.adapter.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.dymek.adapter.repository.dao.ThermometerProfileDao;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.google.common.collect.Lists;

import java.util.List;

public class AndroidThermometerProfileRepository implements ThermometerProfileRepository {
    private ThermometerProfileDao thermometerProfileDao;

    public AndroidThermometerProfileRepository(ThermometerProfileDao thermometerProfileDao) {
        this.thermometerProfileDao = thermometerProfileDao;
    }

    @Override
    public LiveData<List<ThermometerProfile>> getAllThermometerProfiles() {
        return Transformations.map(thermometerProfileDao.getAllThermometerProfiles(), this::convertToThermometerProfiles);
    }

    private List<ThermometerProfile> convertToThermometerProfiles(List<RoomThermometerProfile> thermometerProfiles) {
        List<ThermometerProfile> convertedThermometerProfiles = Lists.newArrayList();
        for (RoomThermometerProfile roomThermometerProfile : thermometerProfiles)
            convertedThermometerProfiles.add(roomThermometerProfile.toThermometerProfile());
        return convertedThermometerProfiles;
    }

    @Override
    public void save(ThermometerProfile thermometerProfile) {
        new AddThermometerProfileAsyncTask(thermometerProfileDao)
                .execute(RoomThermometerProfile.toRoomThermometerProfile(thermometerProfile));
    }

    @Override
    public void update(ThermometerProfile thermometerProfile) {
        new UpdateThermometerProfileAsyncTask(thermometerProfileDao)
                .execute(RoomThermometerProfile.toRoomThermometerProfile(thermometerProfile));
    }

    @Override
    public void delete(ThermometerProfile thermometerProfile) {
        new DeleteThermometerProfileAsyncTask(thermometerProfileDao)
                .execute(RoomThermometerProfile.toRoomThermometerProfile(thermometerProfile));
    }

    private static class AddThermometerProfileAsyncTask extends AsyncTask<RoomThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private AddThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(RoomThermometerProfile... roomThermometerProfile) {
            thermometerProfileDao.save(roomThermometerProfile[0]);
            return null;
        }
    }

    private static class UpdateThermometerProfileAsyncTask extends AsyncTask<RoomThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private UpdateThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(RoomThermometerProfile... roomThermometerProfile) {
            thermometerProfileDao.update(roomThermometerProfile[0]);
            return null;
        }
    }

    private static class DeleteThermometerProfileAsyncTask extends AsyncTask<RoomThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private DeleteThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(RoomThermometerProfile... roomThermometerProfile) {
            thermometerProfileDao.delete(roomThermometerProfile[0]);
            return null;
        }
    }
}
