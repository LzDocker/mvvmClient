package com.docker.mvvmclient.viewmodel;

import android.arch.lifecycle.LiveData;

import com.docker.core.api.ApiResponse;
import com.docker.core.api.BaseResponse;
import com.docker.core.base.BaseViewModel;
import com.docker.mvvmclient.api.AccountService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public class MainViewModel extends BaseViewModel {
    @Inject
    AccountService service;


    @Inject
    public MainViewModel() {

    }

////
//    public LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getData() {
//
//        return service.getBanners();
//    }
}
