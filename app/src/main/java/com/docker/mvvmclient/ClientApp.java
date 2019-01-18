package com.docker.mvvmclient;

import android.content.Context;

import com.docker.accountmodule.di.AccountServiceModule;
import com.docker.core.BaseApplication;
import com.docker.moduleplayer.di.PlayerServiceModule;
import com.docker.mvvmclient.di.DaggerAppComponent;
import com.docker.mvvmclient.di.ServiceModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;

/**
 * Created by zhangxindang on 2018/10/19.
 */

public class ClientApp extends BaseApplication {

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorPrimary);//全局设置主题颜色
                return new ClassicsHeader(context)/*.setTimeFormat(new SimpleDateFormat("更新于 %s"))*/;//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void injectApp() {
        DaggerAppComponent.builder()
                .appModule(getAppModule())
                .globalConfigModule(getGlobalConfigModule())
                .httpClientModule(getHttpClientModule())
                .cacheModule(getCacheModule())
//                .serviceModule(new ServiceModule())
//                .accountServiceModule(new AccountServiceModule())
//                .playerServiceModule(new PlayerServiceModule())
                .build()
                .inject(this);
    }
}
