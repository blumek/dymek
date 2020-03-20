package com.blumek.dymek.shared;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.blumek.dymek.adapter.idGenerator.UUIDGenerator;
import com.blumek.dymek.adapter.repository.AndroidSensorSettingRepository;
import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.adapter.repository.dao.SensorSettingsDao;
import com.blumek.dymek.adapter.repository.dao.ThermometerProfileDao;
import com.blumek.dymek.adapter.repository.dao.ThermometerProfileMetadataDao;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.SensorSettingRepository;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.converters.DateConverters;
import com.blumek.dymek.useCase.CreateSensorSetting;
import com.blumek.dymek.useCase.CreateThermometerProfile;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;

import java.util.Date;

@Database(entities = {
        RoomThermometerProfileMetadata.class,
        RoomSensorSettings.class
        }, version = 2)
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
        private SensorSettingRepository sensorSettingRepository;

        private PopulateDbAsyncTask(AppDatabase database) {
            thermometerProfileRepository =
                    new AndroidThermometerProfileRepository(database.thermometerProfileDao());
            sensorSettingRepository =
                    new AndroidSensorSettingRepository(database.sensorSettingsDao());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            persistFakeData();
            return null;
        }

        private void persistFakeData() {
            ThermometerProfile thermometerProfile = ThermometerProfile.builder()
                    .name("Profile Test 1")
                    .createdAt(new Date())
                    .addSensorSetting(SensorSetting.builder()
                            .name("Settings Test 1")
                            .minTemperatureValue(20)
                            .maxTemperatureValue(40)
                            .build())
                    .addSensorSetting(SensorSetting.builder()
                            .name("Settings Test 2")
                            .minTemperatureValue(10)
                            .maxTemperatureValue(30)
                            .build())
                    .build();

            new CreateThermometerProfile(
                    thermometerProfileRepository,
                    new UUIDGenerator(),
                    new ThermometerProfileValidator(),
                    new CreateSensorSetting(
                            sensorSettingRepository,
                            new UUIDGenerator(),
                            new SensorSettingValidator()
                    )
            ).create(thermometerProfile);
        }
    }
}
