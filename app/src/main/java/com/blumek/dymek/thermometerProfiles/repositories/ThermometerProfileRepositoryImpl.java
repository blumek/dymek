package com.blumek.dymek.thermometerProfiles.repositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileDao;

import java.util.List;

public class ThermometerProfileRepositoryImpl implements ThermometerProfileRepository {
    private ThermometerProfileDao thermometerProfileDao;

    public ThermometerProfileRepositoryImpl(ThermometerProfileDao thermometerProfileDao) {
        this.thermometerProfileDao = thermometerProfileDao;
    }

    @Override
    public LiveData<List<ThermometerProfile>> getAllThermometerProfiles() {
        return thermometerProfileDao.getAllThermometerProfiles();
    }

    @Override
    public void save(ThermometerProfile thermometerProfile) {
        new AddThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    @Override
    public void update(ThermometerProfile thermometerProfile) {
        new UpdateThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    @Override
    public void delete(ThermometerProfile thermometerProfile) {
        new DeleteThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    private static class AddThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private AddThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfile) {
            thermometerProfileDao.save(thermometerProfile[0]);
            return null;
        }
    }

    private static class UpdateThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private UpdateThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfile) {
            thermometerProfileDao.update(thermometerProfile[0]);
            return null;
        }
    }

    private static class DeleteThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private DeleteThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfile) {
            thermometerProfileDao.delete(thermometerProfile[0]);
            return null;
        }
    }
}
