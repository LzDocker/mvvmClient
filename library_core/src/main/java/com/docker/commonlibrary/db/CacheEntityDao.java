package com.docker.commonlibrary.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.docker.commonlibrary.repository.cache.CacheEntity;

@Dao
public interface CacheEntityDao {

    @Query("SELECT * FROM CacheEntity WHERE cachekey = :key")
    LiveData<CacheEntity> LoadCache(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertCache(CacheEntity... cacheEntities);

}
