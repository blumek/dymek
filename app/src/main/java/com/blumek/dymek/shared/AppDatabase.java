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
import com.blumek.dymek.thermometerProfiles.repositories.daos.ThermometerProfileDao;

import java.util.Date;

@Database(entities = {
        ThermometerProfile.class,
        SensorSettings.class
        }, version = 1)
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ThermometerProfileDao thermometerProfileDao;

        private PopulateDbAsyncTask(AppDatabase database) {
            thermometerProfileDao = database.thermometerProfileDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            thermometerProfileDao.insert(new ThermometerProfile("Szynki święta", new Date()));
            thermometerProfileDao.insert(new ThermometerProfile("Kiełbasy", new Date()));
            thermometerProfileDao.insert(new ThermometerProfile("Sery", new Date()));
            return null;
        }
    }

    public abstract ThermometerProfileDao thermometerProfileDao();


}
