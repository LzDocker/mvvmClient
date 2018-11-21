package com.docker.mvvmclient.di;

import com.docker.commonlibrary.di.component.BaseActivityComponent;
import com.docker.commonlibrary.di.scope.ActivityScope;
import com.docker.mvvmclient.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangxindang on 2018/11/21.
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class UIMoudle {

    @ActivityScope
    @ContributesAndroidInjector/*(modules = HomeActivityModule.class)*/
    abstract MainActivity contributeMainActivitytInjector();


}
