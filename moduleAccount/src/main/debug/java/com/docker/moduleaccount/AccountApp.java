package com.docker.moduleaccount;

import com.docker.accountmodule.di.AccountServiceModule;
import com.docker.commonlibrary.BaseApplication;
import com.docker.commonlibrary.di.module.CacheModule;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public class AccountApp extends BaseApplication {

    @Override
    protected void injectApp() {
        DaggerAccountAppComponent.builder().appModule(getAppModule())
                .globalConfigModule(getGlobalConfigModule())
                .httpClientModule(getHttpClientModule())
//                .accountServiceModule(new AccountServiceModule())
                .cacheModule(getCacheModule())
                .build()
                .inject(this);
    }
}
