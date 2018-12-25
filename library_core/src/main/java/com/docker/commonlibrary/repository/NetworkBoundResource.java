package com.docker.commonlibrary.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.vo.Resource;

/**
 * Created by zhangxindang on 2018/12/24.
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {


    // Called to save the result of the API response into the database(存储网络请求返回的数据)
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    // Called with the data in the database to decide whether it should be
    // fetched from the network.（根据数据库检索的结果决定是否需要从网络获取数据）
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    // Called to get the cached data from the database（从数据中获取数据）
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // Called to create the API call.（创建API）
    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.（获取数据失败回调）
    @MainThread
    protected void onFetchFailed() {
    }

    // returns a LiveData that represents the resource, implemented
    // in the base class.（获取LiveData形式的数据）
    public abstract LiveData<Resource<ResultType>> getAsLiveData();
}
