package com.docker.accountmodule.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.vo.BannerVo;
import com.docker.accountmodule.vo.LoginVo;
import com.docker.accountmodule.vo.RegisterVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
import com.docker.commonlibrary.api.CommonCallback;
import com.docker.commonlibrary.api.CommonObserver;
import com.docker.commonlibrary.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/10/19.
 */

public class accountViewModel extends BaseViewModel {

    @Inject
    AccountService service;

    @Inject
    public accountViewModel() {

    }



    public LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanners() {

        return service.getBanners();
    }

    /*
    * 注册
    * */
     public  LiveData<ApiResponse<BaseResponse<LoginVo>>> register(RegisterVo registerVo){

        return service.getRegisterData(registerVo.getUsername().toString().trim(),
                registerVo.getPassword().toString().trim(),registerVo.getRepassword().toString().trim());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
