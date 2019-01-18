package com.docker.moduleaccount;

/**
 * Created by zhangxindang on 2018/11/21.
 */

import com.docker.accountmodule.di.AccountServiceModule;
import com.docker.accountmodule.di.AccountUiModule;
import com.docker.accountmodule.di.AccountViewModelModule;
import com.docker.core.BaseApplication;
import com.docker.core.di.module.AppModule;
import com.docker.core.di.module.BaseViewModelModule;
import com.docker.core.di.module.CacheModule;
import com.docker.core.di.module.GlobalConfigModule;
import com.docker.core.di.module.HttpClientModule;
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
        AccountUiModule.class,
        AccountViewModelModule.class,
        AccountServiceModule.class,
})
public interface AccountAppComponent {

    Gson gson();

    OkHttpClient okHttpClient();

    BaseApplication baseApplication();

    void inject(BaseApplication application);

}
