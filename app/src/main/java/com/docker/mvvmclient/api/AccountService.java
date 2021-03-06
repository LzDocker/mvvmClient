package com.docker.mvvmclient.api;

import android.arch.lifecycle.LiveData;

import com.docker.accountmodule.vo.BannerVo;
import com.docker.commonlibrary.api.ApiResponse;
import com.docker.commonlibrary.api.BaseResponse;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public interface AccountService {

    @GET("banner/json")
    LiveData<ApiResponse<BaseResponse<List<BannerVo>>>> getBanners();

}
