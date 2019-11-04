package com.blumek.dymek.thermometerProfiles.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.AppDatabase;
import com.blumek.dymek.thermometerProfiles.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileDao;

import java.util.List;

public class ThermometerProfileRepository {
    private ThermometerProfileDao thermometerProfileDao;
    private LiveData<List<ThermometerProfile>> allThermometerProfiles;

    public ThermometerProfileRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        thermometerProfileDao = database.thermometerProfileDao();
        allThermometerProfiles = thermometerProfileDao.getThermometerProfiles();
    }

    public void add(ThermometerProfile thermometerProfile) {
        new AddThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    public void update(ThermometerProfile thermometerProfile) {
        new UpdateThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    public void delete(ThermometerProfile thermometerProfile) {
        new DeleteThermometerProfileAsyncTask(thermometerProfileDao).execute(thermometerProfile);
    }

    public LiveData<List<ThermometerProfile>> getThermometerProfiles() {
        return allThermometerProfiles;
    }

    private static class AddThermometerProfileAsyncTask extends AsyncTask<ThermometerProfile, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private AddThermometerProfileAsyncTask(ThermometerProfileDao thermometerProfileDao) {
            this.thermometerProfileDao = thermometerProfileDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfile... thermometerProfiles) {
            thermometerProfileDao.add(thermometerProfiles[0]);
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
