package com.docker.moduleplayer;

import com.docker.commonlibrary.BaseApplication;
import com.docker.commonlibrary.di.module.CacheModule;
import com.docker.moduleplayer.di.PlayerServiceModule;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public class PlayerApp extends BaseApplication {

    @Override
    protected void injectApp() {
        DaggerPlayerAppComponent.builder().appModule(getAppModule())
                .globalConfigModule(getGlobalConfigModule())
                .httpClientModule(getHttpClientModule())
                .playerServiceModule(new PlayerServiceModule())
                .cacheModule(getCacheModule())
                .build()
                .inject(this);
    }
}
