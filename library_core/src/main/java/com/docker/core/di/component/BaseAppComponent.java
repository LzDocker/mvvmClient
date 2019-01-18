package com.docker.core.di.component;

import com.docker.core.BaseApplication;
import com.docker.core.db.CacheDatabase;
import com.docker.core.di.module.AppModule;
import com.docker.core.di.module.CacheModule;
import com.docker.core.di.module.GlobalConfigModule;
import com.docker.core.di.module.HttpClientModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;

/**
 * Created by zhangxindang on 2018/11/21.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        HttpClientModule.class,
        GlobalConfigModule.class,
//        ServiceModule.class,
        CacheModule.class,
})
public interface BaseAppComponent {


    Gson gson();

//    ServiceManager serviceManager();


    OkHttpClient okHttpClient();

    BaseApplication baseApplication();

    void inject(BaseApplication application);

    CacheDatabase cacheDatabase();


}
