//package com.docker.commonlibrary.repository.cache;
//
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MediatorLiveData;
//import android.support.annotation.MainThread;
//import android.support.annotation.NonNull;
//import android.support.annotation.WorkerThread;
//
//import com.docker.commonlibrary.api.ApiResponse;
//import com.docker.commonlibrary.api.BaseResponse;
//import com.docker.commonlibrary.db.CacheDatabase;
//import com.docker.commonlibrary.util.IOUtils;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//@Singleton
//public class CacheManager<RequestType> {
//
//
//    @Inject
//    public CacheManager() { }
//
//
//
//    @WorkerThread
//    public void saveCache(@NonNull ApiResponse<BaseResponse<RequestType>> response, String cacheKey) {
//
//
//    }
//
//
//    @NonNull
//    @MainThread
//    public LiveData<ApiResponse<BaseResponse<RequestType>>> loadCache(String cacheKey) {
//
//    }
//
//
//}
