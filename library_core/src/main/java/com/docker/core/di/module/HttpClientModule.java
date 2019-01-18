package com.docker.core.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.docker.core.BaseApplication;
import com.docker.core.api.RequestInterceptor;
import com.docker.core.db.CacheDatabase;
import com.docker.core.util.LiveDataCallAdapterFactory;
import com.docker.core.util.cookie.CookieJarImpl;
import com.docker.core.util.cookie.PersistentCookieStore;
import com.docker.core.api.RequestInterceptor;
import com.docker.core.util.LiveDataCallAdapterFactory;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangxindang on 2018/11/21.
 */
@Module
public class HttpClientModule {
    private static final int TIME_OUT_SECONDS = 20;
    private Context context;

    public HttpClientModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl) {
        return builder
                .baseUrl(httpUrl)
                .client(client)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder okHttpClient, Interceptor intercept
            , List<Interceptor> interceptors, CookieJar cookieJar) {
        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .addNetworkInterceptor(intercept);

        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }


    @Singleton
    @Provides
    CookieJar providerCookieJar() {
        return new CookieJarImpl(new PersistentCookieStore(context));
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Interceptor provideIntercept(RequestInterceptor interceptor) {
        return interceptor;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }




}