package com.docker.commonlibrary.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

/**
 * Created by zhangxindang on 2018/8/21.
 */

public class BaseViewModel extends ViewModel implements LifecycleObserver {

    /*
    * 通用VM Livedata 传递消息给UI
    * */
    public final MutableLiveData<Pair<Integer,String>> commonMLD = new MutableLiveData<>();



    public BaseViewModel() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {

    }
}

