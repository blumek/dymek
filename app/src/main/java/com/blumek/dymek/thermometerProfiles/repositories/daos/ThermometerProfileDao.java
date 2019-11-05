package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.shared.BaseDao;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;

import java.util.List;

@Dao
public interface ThermometerProfileDao extends BaseDao<ThermometerProfile> {

    @Query("SELECT * FROM ThermometerProfile")
    LiveData<List<ThermometerProfile>> getAllThermometerProfiles();

    @Query("SELECT * FROM ThermometerProfile WHERE id=:id")
    LiveData<ThermometerProfile> getThermometerProfileById(int id);
}
