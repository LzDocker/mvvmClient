package com.docker.accountmodule.di;

import android.content.Context;

import com.docker.accountmodule.api.AccountService;

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


    @Singleton
    @Provides
    AccountService provideUserInfoService(Retrofit retrofit) {
        return retrofit.create(AccountService.class);
    }

}
