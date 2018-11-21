package com.docker.commonlibrary.di.component;

import com.docker.commonlibrary.base.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by zhangxindang on 2018/11/21.
 */

@Subcomponent(modules = {
        AndroidInjectionModule.class,
})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity> {
    }

}
