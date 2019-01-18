package com.docker.core.di.module;

import android.arch.persistence.room.Room;

import com.docker.core.BaseApplication;
import com.docker.core.db.CacheDatabase;
import com.docker.core.repository.cache.CacheEntity;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangxindang on 2018/11/21.
 */

@Module
public class CacheModule {

    @Inject
    public CacheModule(BaseApplication application){}

    @Provides
    @Singleton
    CacheDatabase provideCacheDatabase(BaseApplication application) {
        return Room.databaseBuilder(application, CacheDatabase.class, "Cache.db").build();
    }
}
