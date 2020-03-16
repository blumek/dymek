package com.blumek.dymek.adapter.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.adapter.repository.dao.ThermometerProfileDao;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;

import java.util.List;

public class AndroidThermometerProfileRepository implements ThermometerProfileRepository {
    private ThermometerProfileDao thermometerProfileDao;

    public AndroidThermometerProfileRepository(ThermometerProfileDao thermometerProfileDao) {
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
