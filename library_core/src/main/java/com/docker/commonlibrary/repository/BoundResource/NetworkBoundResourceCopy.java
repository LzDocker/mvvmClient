package com.docker.commonlibrary.repository.BoundResource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;


/**
 * Created by zhangxindang on 2018/12/25.
 */

public abstract class NetworkBoundResourceCopy<ResultType, RequestType> {
    private final AppExecutors appExecutors;
    private final CacheStrategy cacheStrategy;
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResourceCopy(AppExecutors appExecutors, CacheStrategy cacheStrategy) {
        this.appExecutors = appExecutors;
        setZoneValue(Resource.loading(null));
        this.cacheStrategy = cacheStrategy;
        start();
    }


    private void start() {

        switch (cacheStrategy) {
            case NO_CACHE:
                NO_ChCHE();
                break;
            case IF_NONE_CACHE_REQUEST:
                IF_NONE_CACHE_REQUEST();
                break;
            case FIRST_CACHE_THEN_REQUEST:
                FIRST_CACHE_THEN_REQUEST();
                break;
            case REQUEST_FAILED_READ_CACHE:
                REQUEST_FAILED_READ_CACHE();
                break;
        }
    }


    private void NO_ChCHE() {
        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(apiResponse,
                            newData -> {
                                result.removeSource(apiResponse);
                                setZoneValue(Resource.error(response.body.getErrorMsg(), null));
                            });
                } else {
                    appExecutors.diskIO().execute(() -> {
                        saveCallResult(processResponse(response));
                        appExecutors.mainThread().execute(() -> {
                                    LiveData<ResultType> dbSource1 = loadFromDb();
                                    result.addSource(dbSource1,
                                            newData -> {
                                                result.removeSource(dbSource1);
                                                setZoneValue(Resource.success(newData));
                                            });
                                }
                        );
                    });
                }
            } else {
                onFetchFailed();
                result.addSource(apiResponse,
                        newData -> {
                            result.removeSource(apiResponse);
                            setZoneValue(Resource.error(response.errorMessage, null));
                        });
            }
        });

    }

    private void FIRST_CACHE_THEN_REQUEST() {
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            fetchFromNetwork(dbSource);
        });
    }

    private void IF_NONE_CACHE_REQUEST() {

        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, newdata -> {
            result.removeSource(dbSource);
            if (newdata != null) {
                setZoneValue(Resource.success(newdata));
            } else {

                LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
                result.addSource(apiResponse, response -> {
                    result.removeSource(apiResponse);
                    if (response.isSuccessful()) {
                        if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                            result.addSource(apiResponse,
                                    newData -> {
                                        result.removeSource(apiResponse);
                                        setZoneValue(Resource.error(response.body.getErrorMsg(), null));
                                    });
                        } else {
                            appExecutors.diskIO().execute(() -> {
                                saveCallResult(processResponse(response));
                                appExecutors.mainThread().execute(() -> {
                                            LiveData<ResultType> dbSource1 = loadFromDb();
                                            result.addSource(dbSource1,
                                                    newData -> {
                                                        result.removeSource(dbSource1);
                                                        setZoneValue(Resource.success(newData));
                                                    });
                                        }
                                );
                            });
                        }
                    } else {
                        onFetchFailed();
                        result.addSource(apiResponse,
                                newData -> {
                                    result.removeSource(apiResponse);
                                    setZoneValue(Resource.error(response.errorMessage, null));
                                });
                    }
                });
            }
        });
    }

    private void REQUEST_FAILED_READ_CACHE() {

        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(apiResponse,
                            newData -> {
                                result.removeSource(apiResponse);
                                setZoneValue(Resource.error(response.body.getErrorMsg(), null));
                            });
                } else {
                    appExecutors.diskIO().execute(() -> {
                        saveCallResult(processResponse(response));
                        appExecutors.mainThread().execute(() -> {
                                    LiveData<ResultType> dbSource1 = loadFromDb();
                                    result.addSource(dbSource1,
                                            newData -> {
                                                result.removeSource(dbSource1);
                                                setZoneValue(Resource.success(newData));
                                            });
                                }
                        );
                    });
                }
            } else {
                onFetchFailed();
                result.addSource(apiResponse,
                        newData -> {
                            result.removeSource(apiResponse);
                            setZoneValue(Resource.error(response.errorMessage, null));
                            LiveData<ResultType> dbSource = loadFromDb();
                            result.addSource(dbSource, newdata -> {
                                result.removeSource(dbSource);
                                if (newdata == null) {
                                    setZoneValue(Resource.error(response.errorMessage, null));
                                } else {
                                    setZoneValue(Resource.success(newdata));
                                }
                            });
                        });
            }
        });

    }


    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource, newData -> setZoneValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            //noinspection ConstantConditions
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(dbSource,
                            newData -> setZoneValue(Resource.error(response.body.getErrorMsg(), newData)));
                } else {
                    appExecutors.diskIO().execute(() -> {
                        saveCallResult(processResponse(response));
                        appExecutors.mainThread().execute(() -> {
                                    LiveData<ResultType> dbSource1 = loadFromDb();
                                    result.addSource(dbSource1,
                                            newData -> {
                                                result.removeSource(dbSource1);
                                                NetworkBoundResourceCopy.this.setZoneValue(Resource.success(newData));
                                            });
                                }
                        );
                    });
                }
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setZoneValue(Resource.error(response.errorMessage, newData)));
            }
        });
    }


    @MainThread
    private void setZoneValue(Resource<ResultType> newValue) {
        Log.d("ZoneValue", ": -----------setZoneValue----------" + newValue.status);
        result.setValue(newValue);
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<BaseResponse<RequestType>> response) {
        return response.body.getData();
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<BaseResponse<RequestType>>> createCall();
}

