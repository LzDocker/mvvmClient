package com.docker.accountmodule.viewmodel;

import android.arch.lifecycle.LiveData;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.repository.AccountRepository;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.base.BaseViewModel;
import com.docker.commonlibrary.util.AppExecutors;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/10/19.
 */

public class accountViewModel extends BaseViewModel {

    @Inject
    AccountService service;

    @Inject
    AppExecutors appExecutors;


    @Inject
    AccountRepository accountRepository;

    @Inject
    public accountViewModel() {

    }


    public LiveData<ApiResponse<BaseResponse<LoginVo>>> Login(String username, String pwd) {
        return accountRepository.Login(username, pwd);
    }


    /*
    * 注册
    * */
    public LiveData<ApiResponse<BaseResponse<LoginVo>>> register(RegisterVo registerVo) {

        return service.register(registerVo.getUsername().toString().trim(),
                registerVo.getPassword().toString().trim(), registerVo.getRepassword().toString().trim());
    }

    public LiveData<ApiResponse<BaseResponse<LoginVo>>> login(RegisterVo registerVo) {

        return service.login(registerVo.getUsername().toString().trim(),
                registerVo.getPassword().toString().trim());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
