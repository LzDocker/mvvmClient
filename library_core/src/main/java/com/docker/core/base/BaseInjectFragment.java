package com.docker.core.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public class BaseInjectFragment extends Fragment {

    protected void inject() {
        AndroidSupportInjection.inject(this);
        if (injectRouter())
            ARouter.getInstance().inject(this);
    }

    protected boolean injectRouter() {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        inject();
        super.onAttach(activity);
    }
}
