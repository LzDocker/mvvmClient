package com.docker.mvvmclient;

import com.docker.accountmodule.di.AccountServiceModule;
import com.docker.commonlibrary.BaseApplication;
import com.docker.mvvmclient.di.DaggerAppComponent;
import com.docker.mvvmclient.di.ServiceModule;

/**
 * Created by zhangxindang on 2018/10/19.
 */

public class ClientApp extends BaseApplication {

    @Override
    protected void injectApp() {
        DaggerAppComponent.builder()
                .appModule(getAppModule())
                .globalConfigModule(getGlobalConfigModule())
                .httpClientModule(getHttpClientModule())
                .serviceModule(new ServiceModule())
                .accountServiceModule(new AccountServiceModule())
                .build()
                .inject(this);
    }
}
