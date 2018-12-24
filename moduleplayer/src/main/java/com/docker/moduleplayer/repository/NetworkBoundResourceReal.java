package com.docker.moduleplayer.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.moduleplayer.vo.Resource;

/**
 * Created by zhangxindang on 2018/12/24.
 */

public abstract class NetworkBoundResourceReal<ResultType, RequestType> extends NetworkBoundResource<ResultType, RequestType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors appExecutors;

    @MainThread
    NetworkBoundResourceReal(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource,
                        newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly
        result.addSource(dbSource,
                newData -> result.setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            //noinspection ConstantConditions
            if (response.isSuccessful()) {
                saveResultAndReInit(response);
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> result.setValue(
                                Resource.error(response.errorMessage, newData)));
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(ApiResponse<RequestType> response) {
        appExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                result.addSource(loadFromDb(),
                        newData -> result.postValue(Resource.success(newData)));
            }
        });
    }

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
