package com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileMetadataRepositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileMetadataDao;

import java.util.List;

public class ThermometerProfileMetadataRepositoryImpl implements ThermometerProfileMetadataRepository {
    private ThermometerProfileMetadataDao thermometerProfileMetadataDao;

    public ThermometerProfileMetadataRepositoryImpl(ThermometerProfileMetadataDao thermometerProfileMetadataDao) {
        this.thermometerProfileMetadataDao = thermometerProfileMetadataDao;
    }

    @Override
    public LiveData<List<ThermometerProfileMetadata>> getAllThermometerProfiles() {
        return thermometerProfileMetadataDao.getAllThermometerProfiles();
    }

    @Override
    public LiveData<ThermometerProfileMetadata> getThermometerProfileById(int id) {
        return thermometerProfileMetadataDao.getThermometerProfileById(id);
    }

    @Override
    public void save(ThermometerProfileMetadata thermometerProfileMetadata) {
        new AddThermometerProfileAsyncTask(thermometerProfileMetadataDao).execute(thermometerProfileMetadata);
    }

    @Override
    public void update(ThermometerProfileMetadata thermometerProfileMetadata) {
        new UpdateThermometerProfileAsyncTask(thermometerProfileMetadataDao).execute(thermometerProfileMetadata);
    }

    @Override
    public void delete(ThermometerProfileMetadata thermometerProfileMetadata) {
        new DeleteThermometerProfileAsyncTask(thermometerProfileMetadataDao).execute(thermometerProfileMetadata);
    }

    private static class AddThermometerProfileAsyncTask extends AsyncTask<ThermometerProfileMetadata, Void, Void> {
        private ThermometerProfileMetadataDao thermometerProfileMetadataDao;

        private AddThermometerProfileAsyncTask(ThermometerProfileMetadataDao thermometerProfileMetadataDao) {
            this.thermometerProfileMetadataDao = thermometerProfileMetadataDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfileMetadata... thermometerProfileMetadata) {
            thermometerProfileMetadataDao.save(thermometerProfileMetadata[0]);
            return null;
        }
    }

    private static class UpdateThermometerProfileAsyncTask extends AsyncTask<ThermometerProfileMetadata, Void, Void> {
        private ThermometerProfileMetadataDao thermometerProfileMetadataDao;

        private UpdateThermometerProfileAsyncTask(ThermometerProfileMetadataDao thermometerProfileMetadataDao) {
            this.thermometerProfileMetadataDao = thermometerProfileMetadataDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfileMetadata... thermometerProfileMetadata) {
            thermometerProfileMetadataDao.update(thermometerProfileMetadata[0]);
            return null;
        }
    }

    private static class DeleteThermometerProfileAsyncTask extends AsyncTask<ThermometerProfileMetadata, Void, Void> {
        private ThermometerProfileMetadataDao thermometerProfileMetadataDao;

        private DeleteThermometerProfileAsyncTask(ThermometerProfileMetadataDao thermometerProfileMetadataDao) {
            this.thermometerProfileMetadataDao = thermometerProfileMetadataDao;
        }

        @Override
        protected Void doInBackground(ThermometerProfileMetadata... thermometerProfileMetadata) {
            thermometerProfileMetadataDao.delete(thermometerProfileMetadata[0]);
            return null;
        }
    }
}
