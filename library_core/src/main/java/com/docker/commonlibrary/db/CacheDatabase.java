package com.docker.commonlibrary.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.docker.commonlibrary.repository.cache.CacheEntity;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Database(entities = {CacheEntity.class}, version = 1)
public abstract class CacheDatabase extends RoomDatabase {

    public abstract CacheEntityDao cacheEntityDao();
}
