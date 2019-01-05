package com.docker.commonlibrary.db;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.docker.commonlibrary.repository.cache.CacheEntity;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Database(entities = {CacheEntity.class}, version = 1)
public abstract class CacheDatabase extends RoomDatabase {
    private static CacheDatabase INSTANCE;
    private static final Object sLock = new Object();

    public static CacheDatabase getInstacne(Application context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), CacheDatabase.class, "Cache.db")
                                .build();
            }
            return INSTANCE;
        }

    }


    public abstract CacheEntityDao cacheEntityDao();
}
