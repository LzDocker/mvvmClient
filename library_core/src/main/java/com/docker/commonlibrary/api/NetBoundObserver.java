package com.docker.commonlibrary.api;

import android.support.annotation.Nullable;

import com.docker.commonlibrary.vo.Resource;

/**
 * Created by zhangxindang on 2018/9/6.
 */

public class NetBoundObserver<T> implements android.arch.lifecycle.Observer<Resource<T>> {
    private NetBoundCallback<T> netBoundCallback;
    public NetBoundObserver(NetBoundCallback<T> netBoundCallback) {
        this.netBoundCallback = netBoundCallback;
    }
    @Override
    public void onChanged(@Nullable Resource<T> tResource) {
        netBoundCallback.onComplete();
        switch (tResource.status) {
            case LOADING:
                if (tResource.data != null) { netBoundCallback.onCacheComplete(tResource.data); }
                break;
            case SUCCESS: netBoundCallback.onComplete(tResource);break;
            case BUSSINESSERROR: netBoundCallback.onBusinessError(tResource);break;
            case ERROR: netBoundCallback.onNetworkError(tResource);break;
        }
    }
}
