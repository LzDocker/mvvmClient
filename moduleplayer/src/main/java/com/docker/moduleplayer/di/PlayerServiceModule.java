package com.docker.moduleplayer.di;

import android.arch.persistence.room.Room;

import com.docker.commonlibrary.BaseApplication;
import com.docker.moduleplayer.api.PlayerService;
import com.docker.moduleplayer.db.PlayerDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhangxindang on 2018/12/13.
 */
@Module
public class PlayerServiceModule {


    @Provides
    @Singleton
    PlayerDatabase providePlayerDatabase(BaseApplication application) {
        return Room.databaseBuilder(application, PlayerDatabase.class, "player.db").build();
    }

    @Singleton
    @Provides
    PlayerService providePlayerServiceModule(Retrofit retrofit) {
        return retrofit.create(PlayerService.class);
    }


}
