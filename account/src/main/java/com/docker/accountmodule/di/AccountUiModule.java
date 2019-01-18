package com.docker.accountmodule.di;

import com.docker.accountmodule.ui.accountActivity;
import com.docker.commonlibrary.di.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangxindang on 2018/10/29.
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AccountUiModule {


    @ContributesAndroidInjector
    abstract accountActivity providerAccountActivity();
//
//    @ContributesAndroidInjector(modules = FragmentModule.class)
//    abstract LogisticsInformationActivity provideLogisticsInformationActivity();

}
