package com.docker.accountmodule.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.docker.accountmodule.viewmodel.accountViewModel;
import com.docker.commonlibrary.di.ViewModelKey;
import com.docker.commonlibrary.viewmodel.HivescmViewModelFactory;

import java.util.ResourceBundle;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by zhangxindang on 2018/10/29.
 */
@Module
public abstract class AccountViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(accountViewModel.class)
    abstract ViewModel accountViewModel(accountViewModel model);

}
