package com.docker.commonlibrary.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.vo.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangxindang on 2018/12/24.
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    NetworkBoundResource() {
        result.setValue(Resource.loading(null));   //初始化空数据，开始loading
        LiveData<ResultType> dbSource = loadFromDb(); //从本地数据库查询数据
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);   //移除一个观察者
            if (shouldFetch(data)) {         //判断是否请求网络，如果是则发起网络请求，否则把本地数据给观察者，提示加载成功
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));  //setvalue一次代表通知观察者一次，会收到提示
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData))); //把本地数据给观察者
        createCall().enqueue(new Callback<RequestType>() {
            @Override
            public void onResponse(Call<RequestType> call, Response<RequestType> response) {
                result.removeSource(dbSource);
                saveResultAndReInit(response.body());   //存储网络返回结果
            }

            @Override
            public void onFailure(Call<RequestType> call, Throwable t) {
                onFetchFailed();
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(t.getMessage(), newData))); //返回错误信息
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(RequestType response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);  //存储数据到本地
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(), newData -> result.setValue(Resource.success(newData))); //再从本地查询数据
            }
        }.execute();
    }

    //存储网络访问结果到数据库
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    //是否请求网络
    @MainThread
    protected boolean shouldFetch(@Nullable ResultType data) {
        return true;
    }

    //从数据库取数据
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    //发起网络请求
    @NonNull
    @MainThread
    protected abstract Call<RequestType> createCall();

    //网络请求失败
    @MainThread
    protected void onFetchFailed() {
    }

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
