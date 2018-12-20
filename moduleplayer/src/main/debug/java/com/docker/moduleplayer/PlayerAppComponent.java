package com.docker.moduleplayer;

/**
 * Created by zhangxindang on 2018/11/21.
 */

import com.docker.commonlibrary.BaseApplication;
import com.docker.commonlibrary.di.module.AppModule;
import com.docker.commonlibrary.di.module.BaseViewModelModule;
import com.docker.commonlibrary.di.module.CacheModule;
import com.docker.commonlibrary.di.module.GlobalConfigModule;
import com.docker.commonlibrary.di.module.HttpClientModule;
import com.docker.moduleplayer.di.PlayerServiceModule;
import com.docker.moduleplayer.di.PlayerUiModule;
import com.docker.moduleplayer.di.PlayerViewModelModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        HttpClientModule.class,
        GlobalConfigModule.class,
        CacheModule.class,
        BaseViewModelModule.class,
        PlayerUiModule.class,
        PlayerViewModelModule.class,
        PlayerServiceModule.class

})
public interface PlayerAppComponent {

    Gson gson();

    OkHttpClient okHttpClient();

    BaseApplication baseApplication();

    void inject(BaseApplication application);

}
