package com.docker.commonlibrary.viewmodel;

import android.databinding.ObservableList;
import android.util.Pair;

import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.commonlibrary.constant.Constant;
import com.docker.commonlibrary.vo.Resource;

/*
 *
 * 在VM 中使用此callback 来处理数据
 *
 * 可在VM中分出多个数据源来使 activity（UI）做出响应，职责上更加清晰
 *
 * */
public abstract class CommonVmCallBack<T> {
    public CommonVmCallBack(Resource<T> resource){
        DispatchResource(resource);
    }
    public void DispatchResource(Resource<T> resource) {
        switch (resource.status) {
            case LOADING:
                this.onLoading(resource);
                break;
            case SUCCESS:
                this.onComplete(resource);
                break;
        }
    }

    public abstract void onLoading(Resource<T> resource);

    public abstract void onComplete(Resource<T> resource);
}
