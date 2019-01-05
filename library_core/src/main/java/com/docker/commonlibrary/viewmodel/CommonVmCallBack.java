package com.docker.commonlibrary.viewmodel;

import com.docker.commonlibrary.vo.Resource;
/*
*
* 在VM 中使用此callback 来处理数据
*
* 可在VM中分出多个数据源来使 activity（UI）做出响应，职责上更加清晰
*
* */
public abstract class CommonVmCallBack<T> {

    public void setResource(Resource<T> resource) {
        this.onComplete();
        switch (resource.status) {
            case LOADING:
                this.onLoading(resource);
                break;
            case SUCCESS:
                this.onComplete(resource);
                break;
            case BUSSINESSERROR:
                this.onBusinessError(resource);
                break;
            case ERROR:
                this.onNetworkError(resource);
                break;
        }
    }

    public void onComplete(Resource<T> resource) {
    }

    public void onComplete() {
    }

    public abstract void onBusinessError(Resource<T> resource);

    public abstract void onNetworkError(Resource<T> resource);

    public abstract void onLoading(Resource<T> resource);
}
