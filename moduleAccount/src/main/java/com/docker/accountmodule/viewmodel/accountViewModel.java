package com.docker.accountmodule.viewmodel;

import android.arch.lifecycle.LiveData;

import com.docker.accountmodule.api.AccountService;
import com.docker.accountmodule.vo.BannerVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;
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
        public accountViewModel(){

        }

    public LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanners() {

        return service.getBanners();


    }


}
