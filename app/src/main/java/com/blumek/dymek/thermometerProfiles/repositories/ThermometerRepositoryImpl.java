package com.blumek.dymek.thermometerProfiles.repositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileDao;

import java.util.List;

public class ThermometerRepositoryImpl implements ThermometerProfileRepository {
    private ThermometerProfileDao thermometerProfileDao;

    public ThermometerRepositoryImpl(ThermometerProfileDao thermometerProfileDao) {
        this.thermometerProfileDao = thermometerProfileDao;
    }

    @Override
    public LiveData<List<ThermometerProfile>> getAllThermometerProfiles() {
        return thermometerProfileDao.getAllThermometerProfiles();
    }

    @Override
    public LiveData<ThermometerProfile> getThermometerProfileById(int id) {
        return thermometerProfileDao.getThermometerProfileById(id);
    }

    @Override
    public void add(ThermometerProfile thermometerProfile) {
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
        protected Void doInBackground(ThermometerProfile... thermometerProfiles) {
            thermometerProfileDao.insert(thermometerProfiles[0]);
            return null;
        }
    }

    private static class UpdateThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private UpdateThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfiles) {
            thermometerProfileDao.update(thermometerProfiles[0]);
            return null;
        }
    }

    private static class DeleteThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private DeleteThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfiles) {
            thermometerProfileDao.delete(thermometerProfiles[0]);
            return null;
        }
    }
}
