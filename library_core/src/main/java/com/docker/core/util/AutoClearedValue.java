package com.docker.core.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by zhangxindang on 2018/8/31.
 *
 *
 * fragment 中反注册 databing
 *
 */

public class AutoClearedValue<T> {

    private T value;

    public AutoClearedValue(final Fragment fragment, T value) {
        final FragmentManager fragmentManager = fragment.getFragmentManager();
        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                if(f == fragment) {
                    AutoClearedValue.this.value = null;
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                }
            }
        }, false);
        this.value = value;
    }

    public T get() {
        return this.value;
    }
}
