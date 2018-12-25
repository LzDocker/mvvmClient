package com.docker.accountmodule.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.db.AccountDatabase;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.repository.NetworkBoundResource;
import com.docker.commonlibrary.repository.NetworkBoundResourceReal;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;
import com.docker.constantmodule.Constant.Api;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhangxindang on 2018/12/24.
 */

@Singleton
public class AccountRepository {

    private final AppExecutors appExecutors;
    private final AccountService accountService;

    @Inject
    public AccountRepository(AppExecutors appExecutors, AccountService accountService) {
        this.accountService = accountService;
        this.appExecutors = appExecutors;
    }

    @Inject
    AccountDatabase accountDatabase;

    public LiveData<Resource<LoginVo>> Login(String username, String pwd) {
        return new NetworkBoundResourceReal<LoginVo,LoginVo>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull BaseResponse<LoginVo> item) {
                accountDatabase.accountDao().insertAll(item.getData());
            }
            @Override
            protected boolean shouldFetch(@Nullable LoginVo data) {
                return true;
            }
            @NonNull
            @Override
            protected LiveData <LoginVo> loadFromDb() {
                return accountDatabase.accountDao().LoadAccount(username,pwd);
            }
            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<LoginVo>>> createCall() {
              return accountService.login(username, pwd);
            }

        }.getAsLiveData();
    }





//    public LiveData<Resource<LoginVo>> Login(String username, String pwd) {
//        return new NetworkBoundResourceReal<LoginVo, LoginVo>(appExecutors) {
//
//            @Override
//            protected void saveCallResult(@NonNull LoginVo item) {
//
//            }
//
//            @Override
//            protected boolean shouldFetch(@Nullable LoginVo data) {
//                return false;
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<LoginVo> loadFromDb() {
//                return null;
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<ApiResponse<LoginVo>> createCall() {
//                return accountService.login(username,pwd);
//            }
//        }.getAsLiveData();
//    }



}
