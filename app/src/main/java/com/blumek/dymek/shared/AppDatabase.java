package com.blumek.dymek.shared;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileRepositoryImpl;
import com.blumek.dymek.thermometerProfiles.repositories.daos.SensorSettingsDao;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileDao;
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileMetadataDao;
import com.google.common.collect.Lists;

import java.util.Date;

@Database(entities = {
        ThermometerProfileMetadata.class,
        SensorSettings.class
        }, version = 1)
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract ThermometerProfileMetadataDao thermometerProfileMetadataDao();
    public abstract SensorSettingsDao sensorSettingsDao();
    public abstract ThermometerProfileDao thermometerProfileDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ThermometerProfileRepository thermometerProfileRepository;

        private PopulateDbAsyncTask(AppDatabase database) {
            thermometerProfileRepository =
                    new ThermometerProfileRepositoryImpl(database.thermometerProfileDao());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ThermometerProfileMetadata thermometerProfileMetadata = new ThermometerProfileMetadata("Profile Test 1", new Date());
            SensorSettings sensorSettings1 = new SensorSettings("Settings Test 1", 20, 40);
            SensorSettings sensorSettings2 = new SensorSettings("Settings Test 2", 10, 30);
            ThermometerProfile thermometerProfile = new ThermometerProfile(thermometerProfileMetadata, Lists.newArrayList(sensorSettings1, sensorSettings2));
            thermometerProfileRepository.save(thermometerProfile);
            return null;
        }
    }
}
