package com.blumek.dymek;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.repositories.sensorSettingsRepositories.SensorSettingsRepository;
import com.blumek.dymek.thermometerProfiles.repositories.sensorSettingsRepositories.SensorSettingsRepositoryImpl;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepositoryImpl;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        ThermometerProfileRepository thermometerProfileRepository =
                new ThermometerProfileRepositoryImpl(AppDatabase.getInstance(this).thermometerProfileDao());
        thermometerProfileRepository.getAllThermometerProfiles().observe(this, System.out::println);

        SensorSettingsRepository sensorSettingsRepository =
                new SensorSettingsRepositoryImpl(AppDatabase.getInstance(this).sensorSettingsDao());
        sensorSettingsRepository.getAllSensorsSettings().observe(this, System.out::println);

    }
}
