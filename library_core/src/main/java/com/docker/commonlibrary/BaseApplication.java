package com.docker.commonlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.docker.commonlibrary.api.Api;
import com.docker.commonlibrary.api.HttpRequestHandler;
import com.docker.commonlibrary.di.module.AppModule;
import com.docker.commonlibrary.di.module.CacheModule;
import com.docker.commonlibrary.di.module.GlobalConfigModule;
import com.docker.commonlibrary.di.module.HttpClientModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasContentProviderInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public abstract class BaseApplication extends MultiDexApplication implements HasActivityInjector,
        HasBroadcastReceiverInjector,
        HasFragmentInjector,
        HasServiceInjector,
        HasContentProviderInjector,
        HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;
    @Inject
    DispatchingAndroidInjector<ContentProvider> contentProviderInjector;

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        refWatcher = LeakCanary.install(this);
        initRouter();
        initDI();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    protected void initRouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initDI(){
        BaseApplication.instance = this;
        injectApp();
    }

    /*
    * moudle 单独运行时需要在自己的application中注入
    * */
    abstract protected void injectApp();

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    protected AppModule getAppModule() {
        return new AppModule(this);
    }

    protected GlobalConfigModule getGlobalConfigModule() {
        return GlobalConfigModule.buidler()
                .baseurl(Api.BASE_API)
//                .addInterceptor(new LoggingInterceptor.Builder()
//                        .loggable(BuildConfig.DEBUG)
//                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
//                        .log(Platform.INFO)
//                        .request("Request")
//                        .response("Response")
//                        .build())
                .globeHttpHandler(new HttpRequestHandler() {
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                        return response;
                    }

                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        return request;
                    }
                })
                .build();
    }

    protected HttpClientModule getHttpClientModule() {
        return new HttpClientModule();
    }

//    protected ServiceModule getServiceModule() {
//        return new ServiceModule();
//    }

//    protected CacheModule getCacheModule() {
//        return new CacheModule(ContextCompat.getExternalCacheDirs(this)[0]);
//    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverInjector;
    }
    @Override
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        return contentProviderInjector;
    }
    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }
    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
