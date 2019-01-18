package com.docker.core.di.module;

import com.docker.core.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangxindang on 2018/11/21.
 */
@Module
public class AppModule {

    private BaseApplication application;

    public AppModule(BaseApplication application){
        this.application = application;
    }

    @Singleton
    @Provides
    public BaseApplication provideApplication() {
        return application;
    }

}
