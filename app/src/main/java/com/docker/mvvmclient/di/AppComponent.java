package com.docker.mvvmclient.di;

import com.docker.accountmodule.di.AccountServiceModule;
import com.docker.accountmodule.di.AccountUiModule;
import com.docker.accountmodule.di.AccountViewModelModule;
import com.docker.core.BaseApplication;
import com.docker.core.di.module.AppModule;
import com.docker.core.di.module.BaseViewModelModule;
import com.docker.core.di.module.CacheModule;
import com.docker.core.di.module.GlobalConfigModule;
import com.docker.core.di.module.HttpClientModule;
import com.docker.moduleplayer.di.PlayerServiceModule;
import com.docker.moduleplayer.di.PlayerUiModule;
import com.docker.moduleplayer.di.PlayerViewModelModule;
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
        ServiceModule.class,
        CacheModule.class,

        UIMoudle.class,
        ViewModelModule.class,
        BaseViewModelModule.class,

        AccountUiModule.class,
        AccountViewModelModule.class,
        AccountServiceModule.class,

        PlayerUiModule.class,
        PlayerViewModelModule.class,
        PlayerServiceModule.class
        //下面应该是所有module对应的所有Activities:
        //BActivitiesModule.class
        //CActivitiesModule.class
})
public interface AppComponent {

    Gson gson();

    OkHttpClient okHttpClient();

    BaseApplication baseApplication();

    void inject(BaseApplication application);

}
