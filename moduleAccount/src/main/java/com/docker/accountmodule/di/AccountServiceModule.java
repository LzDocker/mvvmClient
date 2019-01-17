package com.docker.accountmodule.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.db.AccountDatabase;
import com.docker.commonlibrary.BaseApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhangxindang on 2018/10/29.
 */

@Module
public class AccountServiceModule {

    @Provides
    @Singleton
    AccountDatabase provideMovieDatabase(BaseApplication application) {
        return Room.databaseBuilder(application, AccountDatabase.class, "account.db").build();
    }

    @Singleton
    @Provides
    AccountService provideUserInfoService(Retrofit retrofit) {
        return retrofit.create(AccountService.class);
    }

}
