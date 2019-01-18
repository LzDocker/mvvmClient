package com.docker.core.di.component;

import android.content.BroadcastReceiver;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by zhangxindang on 2018/11/21.
 */

@Subcomponent(modules = {
        AndroidInjectionModule.class,
})
public interface BroadcastsComponent extends AndroidInjector<BroadcastReceiver> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BroadcastReceiver> {
    }
}