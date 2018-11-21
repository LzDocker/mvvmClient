package com.docker.mvvmclient.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.docker.commonlibrary.di.ViewModelKey;
import com.docker.commonlibrary.viewmodel.HivescmViewModelFactory;
import com.docker.mvvmclient.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by zhangxindang on 2018/11/21.
 */
@Module
public abstract class ViewModelModule {
//
//    @Binds
//    abstract ViewModelProvider.Factory bindViewModelFactory(HivescmViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel MainViewModel(MainViewModel model);


}
