package com.docker.moduleplayer.di;

import com.docker.moduleplayer.api.PlayerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhangxindang on 2018/12/13.
 */
@Module
public class PlayerServiceModule {


    @Singleton
    @Provides
    PlayerService providePlayerServiceModule(Retrofit retrofit) {
        return retrofit.create(PlayerService.class);
    }


}
