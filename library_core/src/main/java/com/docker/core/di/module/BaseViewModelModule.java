package com.docker.core.di.module;

import android.arch.lifecycle.ViewModelProvider;

import com.docker.core.viewmodel.HivescmViewModelFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by zhangxindang on 2018/11/21.
 */
@Module
public abstract class BaseViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(HivescmViewModelFactory factory);

}
