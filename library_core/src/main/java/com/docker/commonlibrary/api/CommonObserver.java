package com.docker.commonlibrary.api;
import android.support.annotation.Nullable;

/**
 * Created by zhangxindang on 2018/9/6.
 */

public  class CommonObserver<T> implements android.arch.lifecycle.Observer<ApiResponse<BaseResponse<T>>> {

    private CommonCallback<T> commonCallback;

    public CommonObserver(CommonCallback<T> commonCallback) {
        this.commonCallback = commonCallback;
    }
    public void onChanged(@Nullable ApiResponse<BaseResponse<T>> apiResponse) {
        this.commonCallback.onComplete();
        if(apiResponse.code != 200) {
            this.commonCallback.onNetworkError(apiResponse);
        } else if(apiResponse.body == null) {
            this.commonCallback.onBusinessError();
        } else {
            this.commonCallback.onComplete((BaseResponse)apiResponse.body);
            this.commonCallback.onComplete((T)((BaseResponse)apiResponse.body).getData());
        }
    }
}
