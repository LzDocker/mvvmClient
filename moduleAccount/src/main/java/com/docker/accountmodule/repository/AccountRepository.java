package com.docker.accountmodule.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.db.AccountDatabase;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.repository.BoundResource.NetworkBoundResource;
import com.docker.commonlibrary.util.AppExecutors;
import com.docker.commonlibrary.vo.Resource;

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
        return new NetworkBoundResource<LoginVo, LoginVo>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull LoginVo item) {
                accountDatabase.accountDao().insertAll(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable LoginVo data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<LoginVo> loadFromDb() {
                return accountDatabase.accountDao().LoadAccount(username);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<BaseResponse<LoginVo>>> createCall() {
                return accountService.login(username, pwd);
            }
        }.asLiveData();
    }


}
