package com.docker.moduleplayer.di;

import android.arch.lifecycle.ViewModel;

import com.docker.commonlibrary.di.ViewModelKey;
import com.docker.moduleplayer.viewmodel.PlayerhomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by zhangxindang on 2018/10/29.
 */
@Module
public abstract class PlayerViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(PlayerhomeViewModel.class)
    abstract ViewModel PlayerhomeViewModel(PlayerhomeViewModel model);

}
