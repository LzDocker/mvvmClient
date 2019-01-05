package com.docker.commonlibrary.api;

import com.docker.commonlibrary.vo.Resource;

/**
 * Created by zhangxindang on 2018/9/6.
 */

public abstract class NetBoundCallback<T> {

    public NetBoundCallback() {
    }

    /*
     * 缓存读取成功
     * */
    public void onCacheComplete(T Result) {
    }

    public void onComplete(Resource<T> resource) {
    }

    public void onComplete() {
    }

    public abstract void onBusinessError(Resource<T> resource);

    public abstract void onNetworkError(Resource<T> resource);
}
