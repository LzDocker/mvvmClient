package com.docker.commonlibrary.repository.BoundResource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.db.CacheDatabase;
import com.docker.commonlibrary.repository.cache.CacheEntity;
import com.docker.commonlibrary.repository.cache.CacheStrategy;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.util.IOUtils;
import com.docker.commonlibrary.vo.Resource;


/**
 * Created by zhangxindang on 2018/12/25.
 */

public abstract class NetworkBoundResourceAuto<ResultType, RequestType> {
    private AppExecutors appExecutors;
    private CacheStrategy cacheStrategy;
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private String cachekey;
    private CacheDatabase cacheDatabase;

    /*
     * 需要缓存的构造
     * */
    @MainThread
    public NetworkBoundResourceAuto(AppExecutors appExecutors, CacheStrategy cacheStrategy, CacheDatabase cacheDatabase, String cachekey) {
        this.appExecutors = appExecutors;
        setZoneValue(Resource.loading(null));
        this.cacheStrategy = cacheStrategy;
        this.cacheDatabase = cacheDatabase;
        this.cachekey = cachekey;
        start();
    }

    /*
     * 不需要缓存的构造
     * */
    @MainThread
    public NetworkBoundResourceAuto() {
        setZoneValue(Resource.loading(null));
        this.cacheStrategy = CacheStrategy.NO_CACHE;
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
    /*
     * 回调 ---》
     *
     * ===> loading ===  bussiness error / networkerror
     *
     * ===>  loading === success
     * */
    private void NO_ChCHE() {
        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(apiResponse,
                            newData -> {
                                result.removeSource(apiResponse);
                                setZoneValue(Resource.bussinessError(response.body.getErrorMsg(), null));
                            });
                } else {
                    setZoneValue(Resource.success((ResultType) response.body.getData()));
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
    /*
     * 回调 ---》 第二个loading带着缓存数据，但缓存不保证不为空
     *
     * ===> loading === loading====> bussiness error / networkerror
     *
     * ===>  loading ===loading ===> success
     * */

    private void FIRST_CACHE_THEN_REQUEST() {
        LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            fetchFromNetwork(dbSource);
        });
    }

    /*
     * 回调 ---》 有缓存直接返回，没有就请求网络 success 中包含的数据就是可用数据
     *
     * ===> loading ====> bussiness error / networkerror
     *
     * ===>  loading  ===> success
     * */
    private void IF_NONE_CACHE_REQUEST() {
        LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource = loadFromDb();
        result.addSource(dbSource, newdata -> {
            result.removeSource(dbSource);
            if (newdata != null) {
                setZoneValue(Resource.success((ResultType) newdata.body.getData()));
            } else {
                LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
                result.addSource(apiResponse, response -> {
                    result.removeSource(apiResponse);
                    if (response.isSuccessful()) {
                        if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                            result.addSource(apiResponse,
                                    newData -> {
                                        result.removeSource(apiResponse);
                                        setZoneValue(Resource.bussinessError(response.body.getErrorMsg(), null));
                                    });
                        } else {
                            appExecutors.diskIO().execute(() -> {
                                saveCallResult(response);
                                appExecutors.mainThread().execute(() -> {
                                            LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource1 = loadFromDb();
                                            result.addSource(dbSource1,
                                                    newData -> {
                                                        result.removeSource(dbSource1);
                                                        setZoneValue(Resource.success((ResultType) newData.body.getData()));
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
    /*
     * 回调 ---》 网络成功 ---》 loading ---- success
     *           网络失败 ---》 loading ----- error / bussiness error ---> success(可能是空的缓存)
     *
     * ===> loading ====> bussiness error / networkerror
     *
     * ===>  loading  ===> success
     * */

    private void REQUEST_FAILED_READ_CACHE() {
        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(apiResponse,
                            newData -> {
                                result.removeSource(apiResponse);
                                setZoneValue(Resource.bussinessError(response.body.getErrorMsg(), null));
                                LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource = loadFromDb();
                                result.addSource(dbSource, newdata -> {
                                    result.removeSource(dbSource);
                                    setZoneValue(Resource.success((ResultType) newdata.body.getData()));
                                });
                            });
                } else {
                    appExecutors.diskIO().execute(() -> {
                        saveCallResult(response);
                        appExecutors.mainThread().execute(() -> {
                                    LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource1 = loadFromDb();
                                    result.addSource(dbSource1,
                                            newData -> {
                                                result.removeSource(dbSource1);
                                                setZoneValue(Resource.success((ResultType) newData.body.getData()));
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
                            LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource = loadFromDb();
                            result.addSource(dbSource, newdata -> {
                                result.removeSource(dbSource);
                                if (newdata != null) {
                                    setZoneValue(Resource.success((ResultType) newdata.body.getData()));
                                } else {
                                    setZoneValue(Resource.success(null));
                                }
                            });
                        });
            }
        });
    }
    private void fetchFromNetwork(final LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource) {
        LiveData<ApiResponse<BaseResponse<RequestType>>> apiResponse = createCall();
        result.addSource(dbSource, newData -> {
            if (newData != null) {
                NetworkBoundResourceAuto.this.setZoneValue(Resource.loading((ResultType) newData.body.getData()));
            } else {
                NetworkBoundResourceAuto.this.setZoneValue(Resource.loading(null));
            }
        });
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            if (response.isSuccessful()) {
                if ("-1".equals(response.body.getErrorCode())) { // bussiness error
                    result.addSource(dbSource,
                            newData -> setZoneValue(Resource.bussinessError(response.body.getErrorMsg(), null)));
                } else {
                    appExecutors.diskIO().execute(() -> {
                        saveCallResult(response);
                        appExecutors.mainThread().execute(() -> {
                                    LiveData<ApiResponse<BaseResponse<RequestType>>> dbSource1 = loadFromDb();
                                    result.addSource(dbSource1,
                                            newData -> {
                                                result.removeSource(dbSource1);
                                                    NetworkBoundResourceAuto.this.setZoneValue(Resource.success((ResultType) newData.body.getData()));
                                            });
                                }
                        );
                    });
                }
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setZoneValue(Resource.error(response.errorMessage, (ResultType) newData.body.getData())));
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
    protected void saveCallResult(@NonNull ApiResponse<BaseResponse<RequestType>> response) {
        CacheEntity cacheEntity = new CacheEntity();
        cacheEntity.setKey(cachekey);
        cacheEntity.setData(IOUtils.toByteArray(response));
    }
    @NonNull
    @MainThread
    protected LiveData<ApiResponse<BaseResponse<RequestType>>> loadFromDb() {
        final MediatorLiveData<ApiResponse<BaseResponse<RequestType>>> responseMediatorLiveData = new MediatorLiveData<>();
        LiveData<CacheEntity> souce = cacheDatabase.cacheEntityDao().LoadCache(cachekey);
        responseMediatorLiveData.addSource(souce, newdata -> {
            responseMediatorLiveData.removeSource(souce);
            if (newdata != null && newdata.getData() != null) {
                responseMediatorLiveData.setValue((ApiResponse<BaseResponse<RequestType>>) IOUtils.toObject(newdata.getData()));
            } else {
                responseMediatorLiveData.setValue(null);
            }
        });
        return responseMediatorLiveData;
    }
    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<BaseResponse<RequestType>>> createCall();
}

