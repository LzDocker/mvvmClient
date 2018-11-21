package com.docker.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public class BaseInjectActivity  extends AppCompatActivity
        implements HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    @MainThread
    protected void inject() {
        AndroidInjection.inject(this);
        if (injectRouter())
            ARouter.getInstance().inject(this);
    }

    protected boolean injectRouter() {
        return false;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }
}
