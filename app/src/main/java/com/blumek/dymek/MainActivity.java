package com.blumek.dymek;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepositoryImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThermometerProfileRepository thermometerProfileRepository = new ThermometerProfileRepositoryImpl(AppDatabase.getInstance(this).thermometerProfileDao());
        thermometerProfileRepository.getAllThermometerProfiles().observe(this, new Observer<List<ThermometerProfile>>() {
            @Override
            public void onChanged(List<ThermometerProfile> thermometerProfiles) {
                System.out.println(thermometerProfiles);
            }
        });
    }
}
